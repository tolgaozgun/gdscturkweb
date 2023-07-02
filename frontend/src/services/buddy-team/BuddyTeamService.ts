import { baseUrl } from '../../constants/api';
import { BuddyTeam } from '../../types/BuddyTeamTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllBuddyTeams(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<BuddyTeam>>>(`${baseUrl}/buddy-teams`);
	return res.data;
}
