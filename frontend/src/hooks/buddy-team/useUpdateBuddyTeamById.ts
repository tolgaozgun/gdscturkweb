import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { updateBuddyTeamById } from '../../services/buddy-team/BuddyTeamService';
import { EditBuddyTeam } from '../../types/BuddyTeamTypes';

const useUpdateBuddyTeamById = (axiosSecure: AxiosInstance, buddyTeamId: number, editBuddyTeam: EditBuddyTeam) => {
	return useQuery({
		queryKey: ['updateBuddyTeamById'],
		queryFn: () => updateBuddyTeamById(axiosSecure, buddyTeamId, editBuddyTeam),
	});
};

export default useUpdateBuddyTeamById;
