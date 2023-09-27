import { AxiosInstance } from "axios";
import { parseCurrentUserUniversityEvents } from "../../services/event/EventService";
import { isErrorResponse } from "../../utils/utils";

export const useParseCurrentUserEvents = (axiosSecure: AxiosInstance) => {
	const parse = async () => {
		const res = await parseCurrentUserUniversityEvents(axiosSecure);
		if (isErrorResponse(res)) {
			return res;
		}
		return res;
	};

	return { parse };
};