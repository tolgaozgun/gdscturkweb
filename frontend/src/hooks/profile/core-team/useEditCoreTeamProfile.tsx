import { AxiosInstance } from "axios";
import { UpdateCoreTeamMemberProfileByStaffRequest } from "../../../types/ProfileTypes";
import { updateCoreTeamProfileByStaff } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";


export const useEditCoreTeamProfile = (axiosSecure: AxiosInstance) => {
	const editCoreTeamProfile = async (updateCoreTeamProfileByStaffRequest: UpdateCoreTeamMemberProfileByStaffRequest, coreTeamMemberId: number) => {
		const res = await updateCoreTeamProfileByStaff(axiosSecure, updateCoreTeamProfileByStaffRequest, coreTeamMemberId);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { editCoreTeamProfile };
};