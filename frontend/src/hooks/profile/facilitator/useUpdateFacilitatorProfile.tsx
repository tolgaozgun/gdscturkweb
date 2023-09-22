import { AxiosInstance } from "axios";
import { updateFacilitatorProfileByFacilitator } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";
import { UpdateFacilitatorProfileByFacilitatorRequest } from "../../../types/ProfileTypes";


export const useUpdateFacilitatorProfile = (axiosSecure: AxiosInstance) => {
	const updateFacilitatorProfile = async (updateFacilitatorProfileByFacilitatorRequest: UpdateFacilitatorProfileByFacilitatorRequest) => {
		const res = await updateFacilitatorProfileByFacilitator(axiosSecure, updateFacilitatorProfileByFacilitatorRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { updateFacilitatorProfile };
};