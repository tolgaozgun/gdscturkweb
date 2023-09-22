import {
  Card,
  Title,
  rem,
  Container,
  Button,
  Group,
  Text,
  Select,
  SelectItem,
  MultiSelect
} from '@mantine/core';
import { useForm } from '@mantine/form';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import useGetBuddyTeamByCurrentFacilitator from '../../../../hooks/buddy-team/useGetBuddyTeamByCurrentFacilitator';
import LoadingLottie from '../../../common/other/LoadingLottie';
import useGetFacilitators from '../../../../hooks/user/useGetFacilitators';
import useGetLeads from '../../../../hooks/user/useGetLeads';
import { forwardRef } from 'react';
import { IconFlag } from '@tabler/icons-react';
import { FacilitatorModel, LeadModel } from '../../../../types';
import { EditBuddyTeam } from '../../../../types/BuddyTeamTypes';
import { updateBuddyTeamById } from '../../../../services/teams/BuddyTeamService';
import { useMutation } from '@tanstack/react-query';
import { notifications } from '@mantine/notifications';
import { isErrorResponse } from '../../../../utils/utils';


interface UserSelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	name: string;
  userId: number;
	picture: string;
	label: string;
}

const UserSelectItem = forwardRef<HTMLDivElement, UserSelectItemProps>(
	({ name, userId, picture, ...others }: UserSelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconFlag />
				{/* {vehicleType === 'PLANE' ? <IconPlane /> : <IconBus />} */}
				<div>
					<Text size="sm">{name}</Text>
				</div>
			</Group>
		</div>
	),
);

const EditBuddyTeamForm = () => {
  const axiosSecure = useAxiosSecure();

  // Fetch the buddy team information using the hook
  const {
    data: buddyTeam,
    isLoading: isBuddyTeamLoading,
    isError: isBuddyTeamError,
  } = useGetBuddyTeamByCurrentFacilitator(axiosSecure);
  
  const {
    data: allFacilitators,
    isLoading: isFacilitatorsLoading,
    isError: isFacilitatorsError,
  } = useGetFacilitators(axiosSecure);

  const {
    data: allLeads,
    isLoading: isLeadsLoading,
    isError: isLeadsError,
  } = useGetLeads(axiosSecure);
    
  
  // Create a form with initial values from the buddyTeam data
  const form = useForm({
    initialValues: {
        facilitator: buddyTeam?.data?.facilitator?.facilitatorId?.toString() || '',
        leads: buddyTeam?.data?.leads?.map((lead) => lead.leadId.toString()) || [],
      // Initialize form fields with buddy team data
      // Example: facilitator: buddyTeam.facilitator?.user?.name || '',
      // Add other fields accordingly
    },
    // Add validation rules as needed
    validate: {
        facilitator: (value) => (value.length > 0 ? null : 'Facilitator is required'),
        leads: (value) => (value.length > 0 ? null : 'Leads are required'),
      // Example: facilitator: (value) => (value.length > 0 ? null : 'Facilitator is required'),
      // Add other validation rules accordingly
    },
  });


  const { mutateAsync: editBuddyTeamMutation } = useMutation({
    mutationKey: ['editBuddyTeam'],
    mutationFn: (editBuddyTeam: EditBuddyTeam) => updateBuddyTeamById(axiosSecure, buddyTeam!.data!.buddyTeamId, editBuddyTeam),
    onError: (error: any) =>
        notifications.show({
            id: 'edit-buddy-team-fail',
            title: 'Update buddy team failed!',
            message: error.response ? error.response.data.msg : 'Something went wrong',
            autoClose: 5000,
            withCloseButton: true,
            style: { backgroundColor: 'red' },
            styles: (theme) => ({
                title: { color: theme.white },
                description: { color: theme.white }
            })
        }),
});


  const handleUpdateBuddyTeam = async () => {
    const validation = form.validate();
    if (validation.hasErrors) {
      return;
    }
    // Prepare the updated buddy team data from the form values
    const updatedBuddyTeam: EditBuddyTeam = {
        // Parse form.values.facilitator to number if needed
        facilitator: Number(form.values.facilitator),
        leads: form.values.leads.map((leadId: string) => Number(leadId)),
      // Example: facilitator: form.values.facilitator,
      // Add other fields accordingly
    };

    try {
      const res = await editBuddyTeamMutation(updatedBuddyTeam);

		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't edit the buddy team",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'Buddy team edited successfully.',
				color: 'green',
			});
		}
      // Handle success, e.g., show a success message
    } catch (error) {
        notifications.show({
            message: error?.toString() || "Something went wrong. Couldn't add the country",
            color: 'red',
        });
      // Handle error, e.g., show an error message
    }
  };


	const leadList: Array<LeadModel> = allLeads?.data || [];
	const leadData: Array<SelectItem> = leadList!
		.map((lead) => {
			return {
				name: lead.user.name + " " + lead.user.surname,
				userId: lead.leadId,
				value: String(lead.leadId),
                picture: lead.user.profileImage,
				label: lead.user.name + " " + lead.user.surname,
			};
		});

	const facilitatorList: Array<FacilitatorModel> = allFacilitators?.data || [];
	const facilitatorData: Array<SelectItem> = facilitatorList!
		.map((facilitator) => {
			return {
				name: facilitator.user.name + " " + facilitator.user.surname,
				userId: facilitator.facilitatorId,
				value: String(facilitator.facilitatorId),
                picture: facilitator.user.profileImage,
				label: facilitator.user.name + " " + facilitator.user.surname,
			};
		});

  if (isBuddyTeamLoading || isFacilitatorsLoading || isLeadsLoading) {
    return <LoadingLottie />;
  }

  if (isBuddyTeamError || isFacilitatorsError || isLeadsError) {
    return <div>Error fetching buddy team data</div>;
  }

  const leadIdArray: string[] = buddyTeam?.data?.leads?.map((lead) => lead.leadId.toString()) || [];

  return (
    <Container>
      <Card shadow="xs" padding="md">
        <Title order={2} style={{ marginBottom: rem(20) }}>
          Edit Buddy Team
        </Title>
        <form>
            <Select
                label="Facilitator"
                placeholder="Pick one"
                itemComponent={UserSelectItem}
                data={facilitatorData}
                defaultValue={buddyTeam?.data?.facilitator?.facilitatorId?.toString() || ''}
                searchable
                withAsterisk
                {...form.getInputProps('facilitator')}
            />
            <MultiSelect
                label="Leads"
                placeholder="Pick multiple"
                itemComponent={UserSelectItem}
                defaultValue={leadIdArray}
                data={leadData}
                searchable
                withAsterisk
                {...form.getInputProps('leads')}
            />

          {/* Add other form fields */}
          <Button
            type="button"
            onClick={handleUpdateBuddyTeam}
            style={{ marginTop: rem(20) }}
          >
            Update Buddy Team
          </Button>
        </form>
      </Card>
    </Container>
  );
};

export default EditBuddyTeamForm;
