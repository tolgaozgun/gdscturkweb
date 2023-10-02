import { AxiosInstance } from "axios";
import { updateLeadProfileByLead } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";
import { UpdateLeadProfileByLeadRequest } from "../../../types/ProfileTypes";


export const useUpdateLeadProfile = (axiosSecure: AxiosInstance) => {
	const updateLeadProfile = async (updateLeadProfileByLeadRequest: UpdateLeadProfileByLeadRequest) => {
		const res = await updateLeadProfileByLead(axiosSecure, updateLeadProfileByLeadRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { updateLeadProfile };
};