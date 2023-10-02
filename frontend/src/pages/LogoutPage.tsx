import { Center, Stack, Text } from '@mantine/core';
import { useLogout } from '../hooks/auth';
import LogoutLottie from '../components/common/other/LogoutLottie';
import { useQuery } from '@tanstack/react-query';
import CheckLottie from '../components/common/other/CheckLottie';
import { useNavigate } from 'react-router';

interface LoadingPageProps {
	message?: string;
}

const LogoutPage = ({ message }: LoadingPageProps) => {

	const {logout} = useLogout();
	const navigate = useNavigate();
	let loggedOut = false;

	const res = useQuery({
		queryKey: ['logout'],
		queryFn: () => logout(),
	});

	if (res?.data) {
		message = 'Logged out successfully! Redirecting to main page..';
		// Wait 2 second
		setTimeout(() => {
			navigate(0)
			navigate("/")
		}, 3000);
		loggedOut = true;
	}

	return (
		<Center sx={{ height: '72vh' }}>
			<Stack align="center">
				{
					!loggedOut && <LogoutLottie />
				}
				{
					loggedOut && <CheckLottie />
				}
				<Text size={22} color="blue">
					{' '}
					{message || 'Logging out..'}
				</Text>
			</Stack>
		</Center>
	);
};

export default LogoutPage;
