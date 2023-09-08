import Cookies from 'js-cookie';
import { registerAdmin } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterAdmin } from '../../types/AuthTypes';

export const useRegisterAdmin = (shouldSetCookie: boolean = true) => {
	const register = async (userDetails: RegisterAdmin) => {
		const res = await registerAdmin(userDetails);
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
