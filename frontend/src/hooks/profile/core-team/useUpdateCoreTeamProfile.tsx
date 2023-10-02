import { AxiosInstance } from "axios";
import { updateCoreTeamProfileByCoreTeam } from "../../../services/profile/ProfileService";
import { UpdateCoreTeamMemberProfileByCoreTeamRequest } from "../../../types/ProfileTypes";
import { isErrorResponse } from "../../../utils/utils";


export const useUpdateCoreTeamProfile = (axiosSecure: AxiosInstance) => {
	const updateCoreTeamProfile = async (updateCoreTeamProfileByCoreTeamRequest: UpdateCoreTeamMemberProfileByCoreTeamRequest) => {
		const res = await updateCoreTeamProfileByCoreTeam(axiosSecure, updateCoreTeamProfileByCoreTeamRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { updateCoreTeamProfile };
};