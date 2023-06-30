import { useEffect } from 'react';
import { axiosSecure } from '../../services/axios';
import { User } from '../../types/UserTypes';
import { useRefresh } from './useRefresh';
import { useUser } from './useUser';
import { NavigateFunction, useNavigate } from 'react-router';
import { notifications } from '@mantine/notifications';

const loginAgain = (navigate: NavigateFunction) => {
	
	navigate("/login");
	notifications.show({
		id: 'login-expired',
		title: 'Login expired!',
		message: 'You need to login again to browse that page!',
		autoClose: 5000,
		withCloseButton: true,
		style: { backgroundColor: 'red' },
		styles: (theme) => ({
			title: { color: theme.white },
			description: { color: theme.white },
		}),
	});
}


const useAxiosSecure = () => {
	const user = useUser();
	const refresh = useRefresh();
	const navigate = useNavigate();

	useEffect(() => {
		const requestIntercept = axiosSecure.interceptors.request.use(
			(config) => {
				if (!config?.headers!['Authorization']) {
					if (user == null) {
						loginAgain(navigate);
					}
					
					if (user?.accessToken == null){
						loginAgain(navigate);
					}

					config.headers!['Authorization'] = `Bearer ${
						(user as User).accessToken
					}`;
				}

				return config;
			},
			(error) => {
				Promise.reject(error)
				console.log("Error occurred here");
			},
		);

		const responseIntercept = axiosSecure.interceptors.response.use(
			(response) => response,
			async (error) => {
				const prevRequest = error?.config;
				if (error?.response?.status === 403 && !prevRequest?.sent) {
					prevRequest.sent = true;
					const newAccessToken = await refresh();
					if (newAccessToken == null) {
						return Promise.reject(error);
					}
					prevRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
					return axiosSecure(prevRequest);
				}
				return Promise.reject(error);
			},
		);

		return () => {
			axiosSecure.interceptors.request.eject(requestIntercept);
			axiosSecure.interceptors.response.eject(responseIntercept);
		};
	}, [user, refresh]);

	return axiosSecure;
};

export default useAxiosSecure;
