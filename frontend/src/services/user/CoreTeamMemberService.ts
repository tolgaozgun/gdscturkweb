import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { CoreTeamMemberModel} from "../../types";
import { AxiosInstance } from 'axios';

export async function getAllCoreTeamMembers(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<CoreTeamMemberModel>>>(`${baseUrl}/core-team-members`);
	return res.data;
}