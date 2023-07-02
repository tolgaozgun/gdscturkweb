import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllBuddyTeams } from '../../services/buddy-team/BuddyTeamService';

const useGetBuddyTeams = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllBuddyTeams'],
		queryFn: () => getAllBuddyTeams(axiosSecure),
	});
};

export default useGetBuddyTeams;
