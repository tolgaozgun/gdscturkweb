import { AxiosInstance } from 'axios';
import { getCurrentUserWithRole } from '../../services/auth';
import { useQuery } from '@tanstack/react-query';

const useGetUserWithRole = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCurrentUserWithRole'],
		queryFn: () => getCurrentUserWithRole(axiosSecure),
	});
};

export default useGetUserWithRole;
