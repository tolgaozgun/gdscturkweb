import {
	Button,
	Card,
	Flex,
	Group,
	PasswordInput,
	Stack,
	TextInput,
	Title,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../constants/colors';

import { useRegisterUser } from '../../hooks/auth';
import { RegisterTraveler, TravelerModel, UserModel, UserType } from '../../types';
import { isErrorResponse } from '../../utils/utils';
import SubtleLinkButton from '../common/buttons/SubtleLinkButton';

const RegisterUserForm = () => {
	const form = useForm({
		initialValues: {
			name: '',
			surname: '',
			email: '',
			password: '',
			confirmPassword: '',
			telephone: '',
		},
		validate: {
			name: (value) => (value === '' ? 'Name ame cannot be left empty.' : null),
			surname: (value) => (value === '' ? 'Surname cannot be left empty.' : null),
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) => (value === '' ? 'Password cannot be left empty.' : null),
			confirmPassword: (value, values) =>
				value !== values.password ? 'Passwords did not match' : null,
			telephone: (value) =>
				/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(value)
					? null
					: 'Invalid phone number',
		},
	});
	const navigate = useNavigate();
	const { register } = useRegisterUser();

	const onRegister = async () => {
		const validation = form.validate();
		console.log(validation);
		if (validation.hasErrors) {
			return;
		}

		const user: UserModel = {
			userId: 0,
			name: form.values.name,
			surname: form.values.surname,
			email: form.values.email,
			password: form.values.password,
			telephone: form.values.telephone,
			userType: UserType.Traveler,
		};
		const traveler: TravelerModel = {
			userId: 0,
			nationality: 'TR',
			balance: 0,
			TCK: '255',
			passportNumber: '255',
		};
		const registerInfo: RegisterTraveler = {
			user,
			traveler,
		};
		console.log(registerInfo);
		const res = await register(registerInfo);
		console.log(res);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'registration-fail',
				title: 'Registration failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red', color: 'white' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			return;
		}

		notifications.show({
			id: 'registration-success',
			title: 'Registration successful!',
			message:
				'You have successfully registered! We are redirecting you to the main page...',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green', color: 'white' },
			styles: (theme) => ({
				title: { color: theme.white },
				description: { color: theme.white }
			})
		});
		navigate('/login');
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Stack spacing="xl">
				<Title size="32px" align="center">
					Register
				</Title>
				<form>
					<Flex direction="column" gap="xs">
						<Flex direction="row" gap="xs">
							<TextInput label="Name" {...form.getInputProps('name')} />
							<TextInput
								label="Surname"
								{...form.getInputProps('surname')}
							/>
						</Flex>
						<TextInput label="Email" {...form.getInputProps('email')} />
						<PasswordInput
							label="Password"
							{...form.getInputProps('password')}
						/>
						<PasswordInput
							label="Confirm Password"
							{...form.getInputProps('confirmPassword')}
						/>
						<TextInput
							label="Telephone"
							{...form.getInputProps('telephone')}
						/>
						<Button bg={primaryButtonColor} onClick={onRegister}>
							Register
						</Button>
						<SubtleLinkButton to="/login" size="sm">
							Already have an account? Login
						</SubtleLinkButton>
						<Group position="center">{}</Group>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default RegisterUserForm;
