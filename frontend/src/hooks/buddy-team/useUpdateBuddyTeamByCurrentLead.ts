import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { updateBuddyTeamByLead } from '../../services/teams/BuddyTeamService';
import { EditBuddyTeam } from '../../types/BuddyTeamTypes';

const useUpdateBuddyTeamByCurrentLead = (axiosSecure: AxiosInstance, editBuddyTeam: EditBuddyTeam) => {
	return useQuery({
		queryKey: ['updateBuddyTeamByLead'],
		queryFn: () => updateBuddyTeamByLead(axiosSecure, editBuddyTeam),
	});
};

export default useUpdateBuddyTeamByCurrentLead;
