import { AxiosInstance } from 'axios';
import { InviteUserRequest } from '../../types/InvitationTypes';
import { inviteUser } from '../../services/invitation/InvitationService';
import { isErrorResponse } from '../../utils/utils';


export const useInviteUser = (axiosSecure: AxiosInstance) => {
	const inviteFunc = async (inviteUserRequest: InviteUserRequest) => {
		const res = await inviteUser(axiosSecure, inviteUserRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { inviteFunc };
};


