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
import { notifications } from '@mantine/notifications';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';
import { useLogin } from '../../../hooks/auth';
import { login } from '../../../services/auth';
import { isErrorResponse } from '../../../utils/utils';
import { useMutation } from '@tanstack/react-query';
import SubtleLinkButton from '../../buttons/SubtleLinkButton';

const LoginForm = () => {
	const navigate = useNavigate();
	const form = useForm({
		initialValues: {
			email: '',
			password: '',
		},

		validate: {
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) =>
				value === '' ? "Can't leave password field empty" : null,
		},
	});

	const { mutate: loginMutation } = useMutation({
		mutationKey: ['login'],
		mutationFn: () => login(form.values.email, form.values.password),
		onSuccess: (data) => {
			if (!data.data) {
				return null;
			}

			notifications.show({
				id: 'login-success',
				title: 'Login successful!',
				message: 'You have successfully logged in!',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'green' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white },
				}),
			});

			Cookies.set('currentUser', JSON.stringify(data.data));
			if (data?.data.userType === 'LEAD') {
				navigate('/add-fare');
			} else if (data.data.userType === 'CORE_TEAM_MEMBER') {
				navigate('/search-fare');
			} else if (data.data.userType === 'FACILITATOR') {
				navigate('/system-reports');
			} else if (data.data.userType === 'GOOGLER') {
				navigate('/system-reports');
			} else if (data.data.userType === 'ADMIN') {
				navigate('/system-reports');
			}
		},
		onError: (error) => {
			notifications.show({
				id: 'login-fail',
				title: 'Login failed!',
				message: error.response
					? error.response.data.msg
					: 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white },
				}),
			});
		},
	});

	const onLogin = async () => {
		// Validate input fields
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		// Call login mutation
		loginMutation();
	};

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 350 }} mx="auto">
			<Stack spacing={'md'}>
				<Title size="28px" align="center">
					Log in to your account
				</Title>
				<SubtleLinkButton to="/register">
					Don't have an account? Register
				</SubtleLinkButton>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<TextInput
							label="Email"
							placeholder="your@email.com"
							{...form.getInputProps('email')}
						/>
						<PasswordInput
							label="Password"
							placeholder="Your password"
							{...form.getInputProps('password')}
						/>
						<Button
							bg={primaryButtonColor}
							loaderPosition="left"
							onClick={onLogin}
						>
							Login
						</Button>
						<Flex
							direction={'row'}
							justify={'space-between'}
							align={'center'}
						>
							<SubtleLinkButton to="/forgot-password" size="sm">
								Forgot Password?
							</SubtleLinkButton>
						</Flex>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default LoginForm;
