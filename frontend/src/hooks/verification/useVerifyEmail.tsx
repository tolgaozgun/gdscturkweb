import { verifyEmail } from "../../services/verification/EmailVerificationService";
import { EmailVerificationRequest } from "../../types/VerificationTypes";
import { isErrorResponse } from "../../utils/utils";


export const useVerifyEmail = () => {
	const verify = async (verifyEmailRequest: EmailVerificationRequest) => {
		const res = await verifyEmail(verifyEmailRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { verify };
};