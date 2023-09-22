import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBuddyTeamByCurrentFacilitator } from '../../services/teams/BuddyTeamService';

const useGetBuddyTeamByCurrentFacilitator = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getBuddyTeamByCurrentFacilitator'],
		queryFn: () => getBuddyTeamByCurrentFacilitator(axiosSecure),
	});
};

export default useGetBuddyTeamByCurrentFacilitator;
