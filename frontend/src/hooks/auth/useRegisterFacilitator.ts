import { registerFacilitator } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterFacilitator } from '../../types/AuthTypes';

export const useRegisterFacilitator = () => {
	const register = async (userDetails: RegisterFacilitator) => {
		const res = await registerFacilitator(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { register };
};
