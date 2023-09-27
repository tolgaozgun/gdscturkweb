import { registerLead } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterLead } from '../../types/AuthTypes';

export const useRegisterLead = () => {
	const register = async (userDetails: RegisterLead) => {
		const res = await registerLead(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { register };
};
