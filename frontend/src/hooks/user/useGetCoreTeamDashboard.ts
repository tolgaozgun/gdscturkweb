import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCoreTeamMemberDashboard } from '../../services/user/DashboardService';

const useGetCoreTeamDashboard = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCoreTeamMemberDashboard'],
		queryFn: () => getCoreTeamMemberDashboard(axiosSecure),
	});
};

export default useGetCoreTeamDashboard;
