import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBuddyTeamByCurrentLead } from '../../services/buddy-team/BuddyTeamService';

const useGetBuddyTeamByCurrentUser = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getBuddyTeamByCurrentUser'],
		queryFn: () => getBuddyTeamByCurrentLead(axiosSecure),
	});
};

export default useGetBuddyTeamByCurrentUser;
