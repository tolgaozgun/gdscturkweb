import { registerCoreTeam } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterCoreTeam } from '../../types/AuthTypes';

export const useRegisterCoreTeam = () => {
	const register = async (userDetails: RegisterCoreTeam) => {
		const res = await registerCoreTeam(userDetails);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { register };
};
