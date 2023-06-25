import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getUniversities } from '../../services/university/UniversityService';

const useGetUniversities = () => {
	return useQuery({
		queryKey: ['getUniversities'],
		queryFn: () => getUniversities(),
	});
};

export default useGetUniversities;
