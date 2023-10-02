import { AxiosInstance } from "axios";
import { updateUserProfileByUser } from "../../../services/profile/ProfileService";
import { isErrorResponse } from "../../../utils/utils";
import { UpdateUserProfileByUserRequest } from "../../../types/ProfileTypes";


export const useUpdateUserProfile = (axiosSecure: AxiosInstance) => {
	const updateUserProfile = async (updateUserProfileUserRequest: UpdateUserProfileByUserRequest) => {
		const res = await updateUserProfileByUser(axiosSecure, updateUserProfileUserRequest);
		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { updateUserProfile };
};