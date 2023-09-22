import { AxiosInstance } from "axios";
import { UpdateLeadProfileByStaffRequest } from "../../../types/ProfileTypes";
import { updateLeadProfileByStaff } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";


export const useEditLeadProfile = (axiosSecure: AxiosInstance) => {
	const editLeadProfile = async (updateLeadProfileByStaffRequest: UpdateLeadProfileByStaffRequest, leadId: number) => {
		const res = await updateLeadProfileByStaff(axiosSecure, updateLeadProfileByStaffRequest, leadId);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { editLeadProfile };
};