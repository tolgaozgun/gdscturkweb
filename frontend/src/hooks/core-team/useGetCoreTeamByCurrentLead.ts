import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCoreTeamByCurrentLead } from '../../services/teams/CoreTeamService';

const useGetCoreTeamByCurrentLead = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCoreTeamByCurrentLead'],
		queryFn: () => getCoreTeamByCurrentLead(axiosSecure),
	});
};

export default useGetCoreTeamByCurrentLead;
