import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBuddyTeamAttendancesByFacilitator } from '../../services/attendance/AttendanceService';


const useGetBuddyTeamAttendanceByFacilitator = (axiosSecure: AxiosInstance, facilitatorId: number) => {
	return useQuery({
		queryKey: ['getBuddyTeamAttendancesByFacilitator'],
		queryFn: () => getBuddyTeamAttendancesByFacilitator(axiosSecure, facilitatorId),
	});
};

export default useGetBuddyTeamAttendanceByFacilitator;
