import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import { CoreTeam } from '../../types/TeamTypes';

export async function getAllCoreTeams(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<CoreTeam>>>(`${baseUrl}/core-teams`);
	return res.data;
}

export async function getCoreTeamByCurrentLead(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<CoreTeam>>(`${baseUrl}/core-teams/current-lead`);
	return res.data;
}

export async function getCoreTeamByCoreTeamMember(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<CoreTeam>>(`${baseUrl}/core-teams/current-member`);
	return res.data;
}

export async function getCoreTeamById(axiosSecure: AxiosInstance, coreTeamId: number) {
	const res = await axiosSecure.post<Response<CoreTeam>>(`${baseUrl}/buddy-teams/${coreTeamId}`);
	return res.data;
}
