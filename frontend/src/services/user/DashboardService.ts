import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { LeadDashboardResponse } from "../../types";
import { AxiosInstance } from 'axios';

export async function getLeadDashboard(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<LeadDashboardResponse>>(`${baseUrl}/leads/dashboard`);
	return res.data;
}