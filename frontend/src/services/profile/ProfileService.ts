import { baseUrl } from '../../constants/api';
import { CoreTeamMemberModel, FacilitatorModel, GooglerModel, LeadModel, UserModel } from '../../types';
import { UpdateCoreTeamMemberProfileByCoreTeamRequest, UpdateCoreTeamMemberProfileByStaffRequest, UpdateFacilitatorProfileByFacilitatorRequest, UpdateFacilitatorProfileByStaffRequest, UpdateGooglerProfileByGooglerRequest, UpdateGooglerProfileByStaffRequest, UpdateLeadProfileByLeadRequest, UpdateLeadProfileByStaffRequest, UpdateUserProfileByStaffRequest, UpdateUserProfileByUserRequest } from '../../types/ProfileTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function updateLeadProfileByLead(axiosSecure: AxiosInstance, updateLeadProfileByLeadRequest: UpdateLeadProfileByLeadRequest) {
	const res = await axiosSecure.post<Response<LeadModel>>(`${baseUrl}/profile/update/lead`, updateLeadProfileByLeadRequest);
	return res.data;
}

export async function updateCoreTeamProfileByCoreTeam(axiosSecure: AxiosInstance, updateCoreTeamProfileByCoreTeamRequest: UpdateCoreTeamMemberProfileByCoreTeamRequest) {
	const res = await axiosSecure.post<Response<CoreTeamMemberModel>>(`${baseUrl}/profile/update/core-team`, updateCoreTeamProfileByCoreTeamRequest);
	return res.data;
}

export async function updateFacilitatorProfileByFacilitator(axiosSecure: AxiosInstance, updateFacilitatorProfileByFacilitatorRequest: UpdateFacilitatorProfileByFacilitatorRequest) {
	const res = await axiosSecure.post<Response<FacilitatorModel>>(`${baseUrl}/profile/update/facilitator`, updateFacilitatorProfileByFacilitatorRequest);
	return res.data;
}

export async function updateGooglerProfileByGoogler(axiosSecure: AxiosInstance, updateGooglerProfileByGooglerRequest: UpdateGooglerProfileByGooglerRequest) {
	const res = await axiosSecure.post<Response<GooglerModel>>(`${baseUrl}/profile/update/googler`, updateGooglerProfileByGooglerRequest);
	return res.data;
}

export async function updateUserProfileByUser(axiosSecure: AxiosInstance, updateUserProfileByUserRequest: UpdateUserProfileByUserRequest) {
	const res = await axiosSecure.post<Response<UserModel>>(`${baseUrl}/profile/update/user`, updateUserProfileByUserRequest);
	return res.data;
}

export async function updateUserProfileByStaff(axiosSecure: AxiosInstance, updateUserProfileByStaffRequest: UpdateUserProfileByStaffRequest, userId: number) {
	const res = await axiosSecure.post<Response<UserModel>>(`${baseUrl}/profile/update-staff/user/${userId}`, updateUserProfileByStaffRequest);
	return res.data;
}

export async function updateLeadProfileByStaff(axiosSecure: AxiosInstance, updateLeadProfileByStaffRequest: UpdateLeadProfileByStaffRequest, leadId: number) {
	const res = await axiosSecure.post<Response<LeadModel>>(`${baseUrl}/profile/update-staff/lead/${leadId}`, updateLeadProfileByStaffRequest);
	return res.data;
} 

export async function updateCoreTeamProfileByStaff(axiosSecure: AxiosInstance, updateCoreTeamProfileByStaffRequest: UpdateCoreTeamMemberProfileByStaffRequest, coreTeamMemberId: number) {
	const res = await axiosSecure.post<Response<CoreTeamMemberModel>>(`${baseUrl}/profile/update-staff/core-team/${coreTeamMemberId}`, updateCoreTeamProfileByStaffRequest);
	return res.data;
}

export async function updateFacilitatorProfileByStaff(axiosSecure: AxiosInstance, updateFacilitatorProfileByStaffRequest: UpdateFacilitatorProfileByStaffRequest, facilitatorId: number) {
	const res = await axiosSecure.post<Response<FacilitatorModel>>(`${baseUrl}/profile/update-staff/facilitator/${facilitatorId}`, updateFacilitatorProfileByStaffRequest);
	return res.data;
}

export async function updateGooglerProfileByStaff(axiosSecure: AxiosInstance, updateGooglerProfileByStaffRequest: UpdateGooglerProfileByStaffRequest, googlerId: number) {
	const res = await axiosSecure.post<Response<GooglerModel>>(`${baseUrl}/profile/update-staff/googler/${googlerId}`, updateGooglerProfileByStaffRequest);
	return res.data;
}