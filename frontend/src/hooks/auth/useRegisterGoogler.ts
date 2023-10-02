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
		return res;
	};

	return { register };
};
