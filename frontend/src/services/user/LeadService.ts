import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { LeadModel } from "../../types";
import { AxiosInstance } from 'axios';

export async function getAllLeads(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<LeadModel>>>(`${baseUrl}/leads`);
	return res.data;
}