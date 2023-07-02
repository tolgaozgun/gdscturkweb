import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllCities } from '../../services/city/CItyService';

const useGetCities = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllCities'],
		queryFn: () => getAllCities(axiosSecure),
	});
};

export default useGetCities;
