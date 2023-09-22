import { AxiosInstance } from 'axios';
import { cancelInvite } from '../../services/invitation/InvitationService';
import { isErrorResponse } from '../../utils/utils';


export const useCancelUserInvite = (axiosSecure: AxiosInstance,) => {
	const cancelFunc = async (invitationId: number) => {
		const res = await cancelInvite(axiosSecure, invitationId);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { cancelFunc };
};

