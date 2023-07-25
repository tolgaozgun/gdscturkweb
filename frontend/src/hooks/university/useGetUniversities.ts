import { useQuery } from '@tanstack/react-query';
import { getUniversities, getUniversitiesAuthed } from '../../services/university/UniversityService';
import { AxiosInstance } from 'axios';

const useGetUniversities = () => {
	return useQuery({
		queryKey: ['getUniversities'],
		queryFn: () => getUniversities(),
	});
};

export const useGetUniversitiesWithAuth = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getUniversitiesAuthed'],
		queryFn: () => getUniversitiesAuthed(axiosSecure),
	});
};

export default useGetUniversities
