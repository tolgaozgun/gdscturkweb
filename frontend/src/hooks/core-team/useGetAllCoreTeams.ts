import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllCoreTeams } from '../../services/teams/CoreTeamService';

const useGetAllCoreTeams = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllCoreTeams'],
		queryFn: () => getAllCoreTeams(axiosSecure),
	});
};

export default useGetAllCoreTeams;
