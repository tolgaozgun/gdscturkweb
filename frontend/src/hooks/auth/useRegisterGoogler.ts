import Cookies from 'js-cookie';
import { registerGoogler } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterGoogler } from '../../types/AuthTypes';

export const useRegisterGoogler = (shouldSetCookie: boolean = true) => {
	const register = async (userDetails: RegisterGoogler) => {
		const res = await registerGoogler(userDetails);
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
