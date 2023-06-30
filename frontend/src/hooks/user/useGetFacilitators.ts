import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllFacilitators } from '../../services/user/FacilitatorService';

const useGetFacilitators = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllFacilitators'],
		queryFn: () => getAllFacilitators(axiosSecure),
	});
};

export default useGetFacilitators;
