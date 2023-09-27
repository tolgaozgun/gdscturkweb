import { registerAdmin } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterAdmin } from '../../types/AuthTypes';

export const useRegisterAdmin = () => {
	const register = async (userDetails: RegisterAdmin) => {
		const res = await registerAdmin(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { register };
};
