import { AxiosInstance } from "axios";
import { UpdateFacilitatorProfileByStaffRequest } from "../../../types/ProfileTypes";
import { updateFacilitatorProfileByStaff } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";


export const useEditFacilitatorProfile = (axiosSecure: AxiosInstance) => {
	const editFacilitatorProfile = async (updateFacilitatorProfileByStaffRequest: UpdateFacilitatorProfileByStaffRequest, facilitatorId: number) => {
		const res = await updateFacilitatorProfileByStaff(axiosSecure, updateFacilitatorProfileByStaffRequest, facilitatorId);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { editFacilitatorProfile };
};