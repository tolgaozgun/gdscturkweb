import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllGooglers } from '../../services/user/GooglerService';

const useGetGooglers = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllGooglers'],
		queryFn: () => getAllGooglers(axiosSecure),
	});
};

export default useGetGooglers;
