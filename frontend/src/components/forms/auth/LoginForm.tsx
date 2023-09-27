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
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';
import { login } from '../../../services/auth';
import { useMutation } from '@tanstack/react-query';
import SubtleLinkButton from '../../buttons/SubtleLinkButton';
import Cookies from 'js-cookie';

const LoginForm = () => {
	const navigate = useNavigate();
	// const { user, isUserLoading, isUserError } = useUser();
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

			// dispatch({type: "LOGIN", payload: data.data});
			
			// let token = {
			// 	accessToken: data.data.accessToken,
			// 	refreshToken: data.data.refreshToken,
			// }

			Cookies.set('accessToken', data.data.accessToken);
			Cookies.set('refreshToken', data.data.refreshToken);
			if (data?.data.userType === 'LEAD') {
				navigate('/panel/lead');
			} else if (data.data.userType === 'CORE_TEAM_MEMBER') {
				navigate('/panel/core-team');
			} else if (data.data.userType === 'FACILITATOR') {
				navigate('/panel/facilitator');
			} else if (data.data.userType === 'GOOGLER') {
				navigate('/panel/googler');
			} else if (data.data.userType === 'ADMIN') {
				navigate('/panel/admin');
			}
			navigate(0)
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
	);
};

export default LoginForm;
