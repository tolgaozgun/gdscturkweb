
import { isErrorResponse } from '../../utils/utils';
import { EmailResendRequest } from '../../types/VerificationTypes';
import { resendEmailVerification } from '../../services/verification/EmailVerificationService';

export const useResendEmailVerification = () => {
	const resendVerification = async (emailResendRequest: EmailResendRequest) => {
		const res = await resendEmailVerification(emailResendRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { resendVerification };
};
