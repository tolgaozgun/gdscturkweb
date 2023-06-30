import Cookies from 'js-cookie';
import { registerGoogler } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterGoogler } from '../../types/AuthTypes';

export const useRegisterGoogler = () => {
	const register = async (userDetails: RegisterGoogler) => {
		const res = await registerGoogler(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		// Set the cookies and return the user
		Cookies.set('currentUser', JSON.stringify(res.data));
		return res;
	};

	return { register };
};
