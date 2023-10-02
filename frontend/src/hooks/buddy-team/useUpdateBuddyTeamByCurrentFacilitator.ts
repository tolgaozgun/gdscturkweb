import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { updateBuddyTeamByFacilitator } from '../../services/teams/BuddyTeamService';
import { EditBuddyTeam } from '../../types/BuddyTeamTypes';

const useUpdateBuddyTeamByCurrentFacilitator = (axiosSecure: AxiosInstance, editBuddyTeam: EditBuddyTeam) => {
	return useQuery({
		queryKey: ['updateBuddyTeamByFacilitator'],
		queryFn: () => updateBuddyTeamByFacilitator(axiosSecure, editBuddyTeam),
	});
};

export default useUpdateBuddyTeamByCurrentFacilitator;
