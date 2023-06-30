import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllLeads } from '../../services/user/LeadService';

const useGetLeads = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllLeads'],
		queryFn: () => getAllLeads(axiosSecure),
	});
};

export default useGetLeads;
