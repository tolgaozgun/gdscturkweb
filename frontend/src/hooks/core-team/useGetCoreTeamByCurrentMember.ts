import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCoreTeamByCoreTeamMember } from '../../services/teams/CoreTeamService';

const useGetCoreTeamByCurrentMember = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCoreTeamByCoreTeamMember'],
		queryFn: () => getCoreTeamByCoreTeamMember(axiosSecure),
	});
};

export default useGetCoreTeamByCurrentMember;
