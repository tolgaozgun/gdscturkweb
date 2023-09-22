import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { CoreTeamMemberDashboardResponse, LeadDashboardResponse } from "../../types";
import { AxiosInstance } from 'axios';

export async function getLeadDashboard(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<LeadDashboardResponse>>(`${baseUrl}/leads/dashboard`);
	return res.data;
}

export async function getCoreTeamMemberDashboard(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<CoreTeamMemberDashboardResponse>>(`${baseUrl}/core-team-members/dashboard`);
	return res.data;
}