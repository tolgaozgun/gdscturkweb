import { AxiosInstance } from 'axios';
import { getCurrentUser } from '../../services/auth';
import { useQuery } from '@tanstack/react-query';

const useUser = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCurrentUser'],
		queryFn: () => getCurrentUser(axiosSecure),
	});
};

export default useUser;
