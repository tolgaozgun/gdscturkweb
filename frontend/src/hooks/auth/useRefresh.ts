import { refresh as refreshFn } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { axiosSecure } from '../../services/axios';
import { useUser } from '../../contexts/UserContext';

export const useRefresh = () => {
	const {user, dispatch} = useUser();

	const token = {
		accessToken: user?.accessToken,
		refreshToken: user?.refreshToken,
	}

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
		
		dispatch({type: "UPDATE_USER", payload: {
			accessToken: res.data.accessToken ? res.data.accessToken : "",
		}})

		return res;
	};

	return refresh;
};


export const useRefreshWithToken = (accessToken: string, refreshToken: string) => {

	const token = {
		accessToken: accessToken,
		refreshToken: refreshToken,
	}

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

		return res;
	};

	return refresh;
};