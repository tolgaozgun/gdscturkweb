import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getLeadDashboard } from '../../services/user/DashboardService';

const useGetLeadDashboard = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getLeadDashboard'],
		queryFn: () => getLeadDashboard(axiosSecure),
	});
};

export default useGetLeadDashboard;
