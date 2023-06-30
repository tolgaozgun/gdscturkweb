import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllCoreTeamMembers } from '../../services/user/CoreTeamMemberService';

const useGetCoreTeamMembers = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllCoreTeamMembers'],
		queryFn: () => getAllCoreTeamMembers(axiosSecure),
	});
};

export default useGetCoreTeamMembers;
