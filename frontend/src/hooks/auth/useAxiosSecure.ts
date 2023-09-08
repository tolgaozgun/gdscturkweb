import { useEffect } from 'react';
import { axiosSecure } from '../../services/axios';
import { User } from '../../types/UserTypes';
import { useRefresh } from './useRefresh';
import { NavigateFunction, useLocation, useNavigate } from 'react-router';
import { notifications } from '@mantine/notifications';
import { useToken } from './useToken';

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


const useAxiosSecure = (shouldRedirect: boolean = true) => {
	const token = useToken();
	const refresh = useRefresh();
	const navigate = useNavigate();
	const location = useLocation();

	useEffect(() => {
		const requestIntercept = axiosSecure.interceptors.request.use(
			(config) => {
				if (!config?.headers!['Authorization']) {
					if (token == null) {
						if (location.pathname !== "/login" && shouldRedirect) {
							loginAgain(navigate);
						}
						return config;
					}
					
					if (token?.accessToken == null){
						if (location.pathname !== "/login" && shouldRedirect) {
							loginAgain(navigate);
						}
						return config;
					}

					config.headers!['Authorization'] = `Bearer ${
						(token as User).accessToken
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
	}, [token, refresh]);

	return axiosSecure;
};

export default useAxiosSecure;
