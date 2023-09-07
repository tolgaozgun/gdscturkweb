import {
	Button,
	Card,
	Flex,
	PasswordInput,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { primaryButtonColor } from '../../../constants/colors';
import { notifications } from '@mantine/notifications';
import { GooglerRegisterModel, RegisterGoogler, UserRegisterModel } from '../../../types';
import SubtleLinkButton from '../../buttons/SubtleLinkButton';
import { isErrorResponse } from '../../../utils/utils';
import { useRegisterGoogler } from '../../../hooks/auth/useRegisterGoogler';


const CreateGooglerForm = () => {

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
	const { register } = useRegisterGoogler();

	

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
		const googler: GooglerRegisterModel = {
		};
		const registerInfo: RegisterGoogler = {
			user,
			googler,
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
				'You have successfully created a new googler!',
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
					Googler Register
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

export default CreateGooglerForm;
