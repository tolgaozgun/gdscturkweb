import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllCampaigns } from '../../services/campaign/CampaignService';


const useGetAllCampaigns = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllCampaigns'],
		queryFn: () => getAllCampaigns(axiosSecure),
	});
};

export default useGetAllCampaigns;
