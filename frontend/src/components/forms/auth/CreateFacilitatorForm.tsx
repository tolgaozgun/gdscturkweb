import {
	Button,
	Card,
	Flex,
	Group,
	PasswordInput,
	Select,
	SelectItem,
	Stack,
	TextInput,
	Title,
	Text,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { primaryButtonColor } from '../../../constants/colors';
import { notifications } from '@mantine/notifications';
import { FacilitatorRegisterModel, RegisterFacilitator, UserRegisterModel } from '../../../types';
import SubtleLinkButton from '../../buttons/SubtleLinkButton';
import { isErrorResponse } from '../../../utils/utils';
import { forwardRef } from 'react';
import { IconSchool } from '@tabler/icons-react';
import { useRegisterFacilitator } from '../../../hooks/auth/useRegisterFacilitator';


interface CreateFacilitatorFormProps {
	universityData: Array<SelectItem>;
}

interface UniversitySelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	label: string;
	description: string;
}

const CustomUniversitySelectItem = forwardRef<HTMLDivElement, UniversitySelectItemProps>(
	({ label, description, ...others }: UniversitySelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconSchool />
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

const CreateFacilitatorForm = ({ universityData }: CreateFacilitatorFormProps) => {
	const form = useForm({
		initialValues: {
			name: '',
			surname: '',
			username:'',
			email: '',
			password: '',
			confirmPassword: '',
			universityId: 0,
		},
		validate: {
			name: (value) =>
				value === '' ? 'Name cannot be left empty.' : null,
			surname: (value) =>
				value === '' ? 'Surname cannot be left empty.' : null,
			username: (value) =>
				value === '' ? 'Username cannot be left empty.' : null,
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) => (value === '' ? 'Password cannot be left empty.' : null),
			confirmPassword: (value, values) =>
				value !== values.password ? 'Passwords did not match' : null,
			universityId: (value) =>
				Number(value) > 0 ? null : 'University must be selected',
		},
	});
	const { register } = useRegisterFacilitator();



	const onRegister = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const user: UserRegisterModel = {
			name: form.values.name,
			surname: form.values.surname,
			username: form.values.username,
			email: form.values.email,
			password: form.values.password,
		};
		const facilitator: FacilitatorRegisterModel = {
			universityId: form.values.universityId,
		};
		const registerInfo: RegisterFacilitator = {
			user,
			facilitator,
		};
		const res = await register(registerInfo);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'creation-fail',
				title: 'Creation failed!',
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
			id: 'creation-success',
			title: 'Creation successful!',
			message:
				'You have successfully created a new facilitator!',
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
					Facilitator Register
				</Title>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Flex direction={'row'} gap={'xs'}>
							<TextInput
								label="Name"
								{...form.getInputProps('name')}
							/>
							<TextInput
								label="Surname"
								{...form.getInputProps('surname')}
							/>
						</Flex>
						<TextInput
							label="Username"
							{...form.getInputProps('username')}
						/>
						<TextInput
							label="Email"
							{...form.getInputProps('email')}
						/>
						<PasswordInput
							label="Password"
							{...form.getInputProps('password')}
						/>
						<PasswordInput
							label="Confirm Password"
							{...form.getInputProps('confirmPassword')}
						/>
						<Select
							label="University"
							placeholder="Pick your university"
							itemComponent={CustomUniversitySelectItem}
							data={universityData}
							searchable
							withAsterisk
							{...form.getInputProps('universityId')}
						/>
						<Button onClick={onRegister} bg={primaryButtonColor}>
							Register
						</Button>
						<SubtleLinkButton to="/login" size="sm">
							Already have an account? Login
						</SubtleLinkButton>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default CreateFacilitatorForm;
