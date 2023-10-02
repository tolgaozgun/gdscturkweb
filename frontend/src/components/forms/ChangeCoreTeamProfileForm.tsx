import {
	Flex,
	TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import CustomElevatedButton from '../buttons/CustomElevatedButton';
import { CoreTeamMemberModel } from '../../types';

interface ChangeCoreTeamProfileFormProps {
	padding?: number;
	mt?: number;
	coreTeamMember: CoreTeamMemberModel;
}

const ChangeCoreTeamProfileForm = ({padding, mt, coreTeamMember}: ChangeCoreTeamProfileFormProps) => {

	const form = useForm({
		initialValues: {
			university: coreTeamMember && coreTeamMember.university ? coreTeamMember.university.name : "",
		},
		validate: {
		},
	});



	// const { mutateAsync: submitProfile } = useMutation({
	// 	mutationKey: ['updateUserProfileByUser'],
	// 	mutationFn: (updateUserProfileByUserRequest: UpdateUserProfileByUserRequest) => updateUserProfileByUser(axiosSecure, updateUserProfileByUserRequest),
	// 	onError: (error: any) =>
	// 		notifications.show({
	// 			id: 'update-profile-fail',
	// 			title: 'Update profile failed!',
	// 			message: error.response ? error.response.data.msg : 'Something went wrong',
	// 			autoClose: 5000,
	// 			withCloseButton: true,
	// 			style: { backgroundColor: 'red' },
	// 			styles: (theme) => ({
	// 				title: { color: theme.white },
	// 				description: { color: theme.white }
	// 			})
	// 		}),
	// });

	// const topicData: Array<SelectItem> = topicList!
	// 	.map((topic) => {
	// 		return {
    //             label: topic.name,
    //             value: String(topic.topicId),
	// 		};
	// 	}).sort((a, b) => a.label.localeCompare(b.label));


	// const handleSubmit = async () => {
	// 	const validation = form.validate();
	// 	if (validation.hasErrors) {
	// 		return;
	// 	}

	// 	const userDetails: UpdateUserProfileByUser = {
	// 		name: form.isTouched('name') ? form.values.name : undefined,
	// 		surname: form.isTouched('surname') ? form.values.surname : undefined,
	// 		profileImage: form.isTouched('profileImage') ? form.values.profileImage : undefined,
	// 		phoneNumber: form.isTouched('phoneNumber') ? form.values.phoneNumber : undefined,
	// 		biography: form.isTouched('biography') ? form.values.biography : undefined,
	// 		interests: form.isTouched('interests') ? form.values.interests : undefined,
	// 	};

	// 	const request: UpdateUserProfileByUserRequest = {
	// 		user: userDetails,
	// 	}

	// 	const res = await submitProfile(request);
	// 	if (isErrorResponse(res)) {
	// 		notifications.show({
	// 			message: res?.msg || "Something went wrong. Couldn't update the profile",
	// 			color: 'red',
	// 		});
	// 	} else {
	// 		notifications.show({
	// 			message: 'Profile updated successfully.',
	// 			color: 'green',
	// 		});
	// 	}
	// };



	return (
			<Flex direction={'column'} gap={'xl'} p={padding} mt={mt}>
            <form>
                <Flex direction={'column'} gap={'xl'}>
                    <TextInput
                        label="University"
						disabled
                        {...form.getInputProps('university')}
						required={false}
                    />
                </Flex>
            </form>
				<Flex gap="md">
					{/* <CustomElevatedButton text={'Cancel'} color="red" /> */}
					<CustomElevatedButton disabled={true} text={'Update'} />
				</Flex>		
        </Flex>
	);
};

export default ChangeCoreTeamProfileForm;
