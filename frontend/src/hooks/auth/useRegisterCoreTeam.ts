import Cookies from 'js-cookie';
import { registerCoreTeam } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { RegisterCoreTeam, RegisterLead } from '../../types/AuthTypes';

export const useRegisterCoreTeam = () => {
	const register = async (userDetails: RegisterCoreTeam) => {
		const res = await registerCoreTeam(userDetails);
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
