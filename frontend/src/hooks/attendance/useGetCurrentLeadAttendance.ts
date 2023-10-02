import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAttendanceListByCurrentLead } from '../../services/attendance/AttendanceService';


const useGetCurrentLeadAttendance = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAttendanceByCurrentLead'],
		queryFn: () => getAttendanceListByCurrentLead(axiosSecure),
	});
};

export default useGetCurrentLeadAttendance;
