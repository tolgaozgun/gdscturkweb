import Cookies from 'js-cookie';
import { registerCoreTeam } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterCoreTeam } from '../../types/AuthTypes';

export const useRegisterCoreTeam = (shouldSetCookie: boolean = true) => {
	const register = async (userDetails: RegisterCoreTeam) => {
		const res = await registerCoreTeam(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		let token = {
			accessToken: res.data.accessToken,
			refreshToken: res.data.refreshToken,
		}
		// Set the cookies and return the user
		if (shouldSetCookie) {
			Cookies.set('token', JSON.stringify(token));
		}
		return res;
	};

	return { register };
};
