import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBuddyTeamByCurrentLead } from '../../services/teams/BuddyTeamService';

const useGetBuddyTeamByCurrentLead = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getBuddyTeamByCurrentUser'],
		queryFn: () => getBuddyTeamByCurrentLead(axiosSecure),
	});
};

export default useGetBuddyTeamByCurrentLead;
