import {
    Affix,
	Flex,
	Group,
	TextInput,
	Text,
    rem,
	SelectItem,
	MultiSelect,
	Input,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import CustomElevatedButton from '../buttons/CustomElevatedButton';
import { UpdateUserProfileByUser, UpdateUserProfileByUserRequest } from '../../types/ProfileTypes';
import { isErrorResponse } from '../../utils/utils';
import { updateUserProfileByUser } from '../../services/profile/ProfileService';
import { IconLanguage } from '@tabler/icons-react';
import { forwardRef } from 'react';
import { Topic } from '../../types/TopicTypes';
import { User } from '../../types';
import { NavigateFunction } from 'react-router';
import CustomTextEditor from './CustomTextEditor';

interface TopicSelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	label: string;
	description: string;
}

const TopicSelectItem = forwardRef<HTMLDivElement, TopicSelectItemProps>(
	({ label, description, ...others }: TopicSelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconLanguage />
				<div>
					<Text size="sm">{label}</Text>
					<Text size="xs" opacity={0.65}>
						{description}
					</Text>
				</div>
			</Group>
		</div>
	),
);

interface ChangeUserProfileFormProps {
	padding?: number;
	mt?: number;
	navigate?: NavigateFunction;
	user: User;
	topicList: Array<Topic>;

}


const ChangeUserProfileForm = ({padding, mt, navigate, user, topicList}: ChangeUserProfileFormProps) => {
	const axiosSecure = useAxiosSecure();
	

	const form = useForm({
		initialValues: {
			name: user && user.name ? user.name : '',
			surname: user && user.surname ? user.surname : '',
			email: user && user.email ? user.email : '',
			username: user && user.username ? user.username : '',
			profileImage: user && user.profileImage ? user.profileImage : '',
			phoneNumber: user && user.phoneNumber ? user.phoneNumber : '',
			biography: user && user.biography ? user.biography : '',
			interests: user && user.interests ? user.interests : [],
		},
		validate: {
			name: (value) => (value && value.length > 0 ? null : 'Name is required.'),
			surname: (value) => (value && value.length > 0 ? null : 'Surname is required.'),
			biography: (value) => (value && value.length > 0 ? null : 'Biography is required.'),
		},
	});

	const changeBiography = (value: string) => {
		form.setFieldValue('biography', value);
	}



	const { mutateAsync: submitProfile } = useMutation({
		mutationKey: ['updateUserProfileByUser'],
		mutationFn: (updateUserProfileByUserRequest: UpdateUserProfileByUserRequest) => updateUserProfileByUser(axiosSecure, updateUserProfileByUserRequest),
		onError: (error: any) =>
			notifications.show({
				id: 'update-profile-fail',
				title: 'Update profile failed!',
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

	const topicData: Array<SelectItem> = topicList!
		.map((topic) => {
			return {
                label: topic.name,
                value: String(topic.topicId),
			};
		}).sort((a, b) => a.label.localeCompare(b.label));


	const handleSubmit = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const userDetails: UpdateUserProfileByUser = {
			name: form.isTouched('name') ? form.values.name : undefined,
			surname: form.isTouched('surname') ? form.values.surname : undefined,
			profileImage: form.isTouched('profileImage') ? form.values.profileImage : undefined,
			phoneNumber: form.isTouched('phoneNumber') ? form.values.phoneNumber : undefined,
			biography: form.isTouched('biography') ? form.values.biography : undefined,
			interests: form.isTouched('interests') ? form.values.interests : undefined,
		};

		const request: UpdateUserProfileByUserRequest = {
			user: userDetails,
		}

		const res = await submitProfile(request);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't update the profile",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'Profile updated successfully.',
				color: 'green',
			});
			if (navigate) {
				navigate(0)
			}
		}
	};



	return (
			<Flex direction={'column'} gap={'xl'} p={padding} mt={mt}>
            <form>
                <Flex direction={'column'} gap={'md'}>
					<Flex direction="row" gap="md">
						<TextInput	
							withAsterisk
							label="Name"
							{...form.getInputProps('name')}
						/>
						<TextInput
							withAsterisk
							label="Surname"
							{...form.getInputProps('surname')}
						/>
					</Flex>
                    <TextInput
                        label="Email"
						disabled
                        {...form.getInputProps('email')}
						required={false}
                    />
                    <TextInput
                        label="Username"
						disabled
                        {...form.getInputProps('username')}
						required={false}
                    />
                    <TextInput
                        withAsterisk
                        label="Profile Image"
                        {...form.getInputProps('profileImage')}
                    />
                    <TextInput
                        withAsterisk
                        label="Phone Number"
                        {...form.getInputProps('phoneNumber')}
                    />

					<Input.Wrapper
                        withAsterisk
                        label="Biography"
                        {...form.getInputProps('biography')}
                    >
						<CustomTextEditor content={user && user.biography ? user.biography : ""} onChange={changeBiography} />

					</Input.Wrapper>
                    <MultiSelect
                        label="Interests"
                        placeholder="Pick your interests"
                        itemComponent={TopicSelectItem}
                        data={topicData}
						defaultValue={user?.interests ? user.interests.map((interest) => String(interest.topicId)) : []}
                        searchable
                        withAsterisk
                        {...form.getInputProps('interests')}
                    />
                </Flex>
            </form>
				<Flex gap="md">
					{/* <CustomElevatedButton text={'Cancel'} color="red" /> */}
					<CustomElevatedButton text={'Update Profile'} onClick={handleSubmit} />
				</Flex>		
        </Flex>
	);
};

export default ChangeUserProfileForm;
