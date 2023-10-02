import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllEvents } from '../../services/event/EventService';

const useGetAllEvents = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllEvents'],
		queryFn: () => getAllEvents(axiosSecure),
	});
};

export default useGetAllEvents;
