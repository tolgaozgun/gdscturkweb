import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import { EmailResendRequest, EmailVerificationRequest } from '../../types/VerificationTypes';
import { axiosSecure as axios } from '../axios';


export async function verifyEmail(
	verifyEmail: EmailVerificationRequest,
): Promise<Response<string>> {
	const res = await axios.post<Response<string>>(
		`${baseUrl}/email-verification/verify`,
		verifyEmail,
	);
	return res.data;
}

export async function resendEmailVerification(
	emailResendRequest: EmailResendRequest,
): Promise<Response<string>> {
	const res = await axios.post<Response<string>>(
		`${baseUrl}/email-verification/resend`,
		emailResendRequest,
	);
	return res.data;
}