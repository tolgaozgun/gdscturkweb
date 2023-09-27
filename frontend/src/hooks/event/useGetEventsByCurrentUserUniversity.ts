import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getEventsByCurrentUserUniversity } from '../../services/event/EventService';

const useGetEventsByCurrentUserUniversity = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getEventsByCurrentUserUniversity'],
		queryFn: () => getEventsByCurrentUserUniversity(axiosSecure),
	});
};

export default useGetEventsByCurrentUserUniversity;
