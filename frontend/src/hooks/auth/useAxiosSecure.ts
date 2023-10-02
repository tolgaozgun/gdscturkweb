import { useEffect } from 'react';
import { axiosSecure } from '../../services/axios';
import { User } from '../../types/UserTypes';
import { useRefresh, useRefreshWithToken } from './useRefresh';
import { NavigateFunction, useLocation, useNavigate } from 'react-router';
import { notifications } from '@mantine/notifications';
import { useToken } from './useToken';

const loginAgain = (navigate: NavigateFunction) => {
	
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

const useAxiosSecureWithToken = (accessToken: string, refreshToken: string) => {
	const refresh = useRefreshWithToken(accessToken, refreshToken);

	const token = {
		accessToken: accessToken,
		refreshToken: refreshToken,
	}

	useEffect(() => {
		const requestIntercept = axiosSecure.interceptors.request.use(
			(config) => {
				if (!config?.headers!['Authorization']) {
					if (token == null) {
						return config;
					}
					
					if (token?.accessToken == null){
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
}


const useAxiosSecure = (shouldRedirect: boolean = true) => {
	const token = useToken();
	const refresh = useRefresh();
	let navigate: NavigateFunction | null = null;
	let location: Location | null = null;
	if (shouldRedirect) {
		navigate = useNavigate();
		location = useLocation();
	}

	// const token = {
	// 	accessToken: user?.accessToken,
	// 	refreshToken: user?.refreshToken,
	// }

	useEffect(() => {
		const requestIntercept = axiosSecure.interceptors.request.use(
			(config) => {
				if (!config?.headers!['Authorization']) {
					if (token == null) {
						if (shouldRedirect && location!.pathname !== "/login") {
							loginAgain(navigate!);
						}
						return config;
					}
					
					if (token?.accessToken == null){
						if (shouldRedirect && location!.pathname !== "/login") {
							loginAgain(navigate!);
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
export { useAxiosSecureWithToken };