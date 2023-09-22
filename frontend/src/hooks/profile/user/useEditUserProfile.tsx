import { AxiosInstance } from "axios";
import { UpdateUserProfileByStaffRequest } from "../../../types/ProfileTypes";
import { updateUserProfileByStaff } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";


export const useEditUserProfile = (axiosSecure: AxiosInstance) => {
	const editUserProfile = async (updateUserProfileByStaffRequest: UpdateUserProfileByStaffRequest, userId: number) => {
		const res = await updateUserProfileByStaff(axiosSecure, updateUserProfileByStaffRequest, userId);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { editLeadProfile: editUserProfile };
};