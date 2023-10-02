import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getFacilitatorDashboard } from '../../services/user/DashboardService';

const useGetFacilitatorDashboard = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getFacilitatorDashboard'],
		queryFn: () => getFacilitatorDashboard(axiosSecure),
	});
};

export default useGetFacilitatorDashboard;
