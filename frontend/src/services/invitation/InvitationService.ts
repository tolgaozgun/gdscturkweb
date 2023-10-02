import { baseUrl } from '../../constants/api';
import { InviteUserRequest, UserInvitationResponse } from '../../types/InvitationTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function inviteUser(axiosSecure: AxiosInstance, inviteUserRequest: InviteUserRequest) {
	const res = await axiosSecure.post<Response<UserInvitationResponse>>(`${baseUrl}/invitation/invite`, inviteUserRequest);
	return res.data;
}

export async function cancelInvite(axiosSecure: AxiosInstance, invitationId: number) {
	const res = await axiosSecure.post<Response<UserInvitationResponse>>(`${baseUrl}/invitation/cancel/${invitationId}`);
	return res.data;
}
