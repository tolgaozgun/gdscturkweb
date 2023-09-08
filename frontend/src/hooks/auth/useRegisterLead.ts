import Cookies from 'js-cookie';
import { registerLead } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterLead } from '../../types/AuthTypes';

export const useRegisterLead = (shouldSetCookie: boolean = false) => {
	const register = async (userDetails: RegisterLead) => {
		const res = await registerLead(userDetails);
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
