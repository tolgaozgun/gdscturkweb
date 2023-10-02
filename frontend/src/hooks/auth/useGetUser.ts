import { AxiosInstance } from 'axios';
import { getCurrentUser } from '../../services/auth';
import { useQuery } from '@tanstack/react-query';

const useGetUser = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCurrentUser'],
		queryFn: () => getCurrentUser(axiosSecure),
	});
};

export default useGetUser;
