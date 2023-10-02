import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getEventsByCurrentUserBuddy } from '../../services/event/EventService';

const useGetEventsByCurrentUserBuddy = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getEventsByCurrentUserBuddy'],
		queryFn: () => getEventsByCurrentUserBuddy(axiosSecure),
	});
};

export default useGetEventsByCurrentUserBuddy;
