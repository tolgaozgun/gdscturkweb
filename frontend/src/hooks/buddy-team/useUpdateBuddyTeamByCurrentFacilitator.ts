import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getBuddyTeamByCurrentFacilitator, updateBuddyTeamByFacilitator } from '../../services/buddy-team/BuddyTeamService';
import { EditAnnouncement } from '../../types/AnnouncementTypes';
import { EditBuddyTeam } from '../../types/BuddyTeamTypes';

const useUpdateBuddyTeamByCurrentFacilitator = (axiosSecure: AxiosInstance, editBuddyTeam: EditBuddyTeam) => {
	return useQuery({
		queryKey: ['updateBuddyTeamByFacilitator'],
		queryFn: () => updateBuddyTeamByFacilitator(axiosSecure, editBuddyTeam),
	});
};

export default useUpdateBuddyTeamByCurrentFacilitator;
