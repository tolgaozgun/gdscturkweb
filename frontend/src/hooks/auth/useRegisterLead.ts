import Cookies from 'js-cookie';
import { registerLead } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterLead } from '../../types/AuthTypes';

export const useRegisterLead = (shouldSetCookie: boolean = true) => {
	const register = async (userDetails: RegisterLead) => {
		const res = await registerLead(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		// Set the cookies and return the user
		if (shouldSetCookie) {
			Cookies.set('currentUser', JSON.stringify(res.data));
		}
		return res;
	};

	return { register };
};
