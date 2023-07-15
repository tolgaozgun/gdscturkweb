import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllCountries } from '../../services/country/CountryService';

const useGetCountries = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllCountries'],
		queryFn: () => getAllCountries(axiosSecure),
	});
};

export default useGetCountries;
