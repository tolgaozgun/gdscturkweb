import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllBuddyTeams } from '../../services/teams/BuddyTeamService';

const useGetAllBuddyTeams = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllBuddyTeams'],
		queryFn: () => getAllBuddyTeams(axiosSecure),
	});
};

export default useGetAllBuddyTeams;
