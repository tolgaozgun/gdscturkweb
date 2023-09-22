import {
	Button,
	Card,
	Flex,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useInviteUser } from '../../../../hooks/invitation/useInviteUser';
import { axiosSecure } from '../../../../services/axios';
import { InviteUserRequest } from '../../../../types/InvitationTypes';
import { UserType } from '../../../../types';
import { primaryButtonColor } from '../../../../constants/colors';
import { isErrorResponse } from '../../../../utils/utils';

const InviteCoreTeamForm = () => {
	const form = useForm({
		initialValues: {
			email: '',
		},
		validate: {
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
		},
	});
	const { inviteFunc } = useInviteUser(axiosSecure);

	

	const onSubmit = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}
		const registerInfo: InviteUserRequest = {
			email: form.values.email,
			role: UserType.CoreTeamMember,
		};
		const res = await inviteFunc(registerInfo);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'invite-fail',
				title: 'Invite failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			return;
		}

		notifications.show({
			id: 'invite-success',
			title: 'Invitation successful!',
			message:
				'You have successfully invited a Core Team Member!',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
			styles: (theme) => ({
				title: { color: theme.white },
				description: { color: theme.white }
			})
		});
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Stack spacing={'xl'}>
				<Title size="32px" align="center">
					Invite to Core Team
				</Title>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							label="Email"
							{...form.getInputProps('email')}
						/>
						<Button onClick={onSubmit} bg={primaryButtonColor}>
							Invite to Core Team
						</Button>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default InviteCoreTeamForm;
