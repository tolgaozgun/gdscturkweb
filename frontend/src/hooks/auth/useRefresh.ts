import Cookies from 'js-cookie';
import { refresh as refreshFn } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { axiosSecure } from '../../services/axios';
import { useToken } from './useToken';

export const useRefresh = () => {
	const token = useToken();

	const refresh = async () => {
		if (!token) {
			return null;
		}

		if(!token.refreshToken) {
			return null;
		}

		const res = await refreshFn(token.refreshToken, axiosSecure);

		if (isErrorResponse(res)) {
			return res;
		}

		Cookies.set(
			'token',
			JSON.stringify({
				...token,
				refreshToken: res.data.refreshToken,
			}),
		);

		return res;
	};

	return refresh;
};
