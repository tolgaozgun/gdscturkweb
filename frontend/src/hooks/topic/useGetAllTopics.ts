import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllTopics } from '../../services/topic/TopicService';

const useGetAllTopics = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllTopics'],
		queryFn: () => getAllTopics(axiosSecure),
	});
};

export default useGetAllTopics;
