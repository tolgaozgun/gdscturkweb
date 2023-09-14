import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCurrentCampaigns } from '../../services/campaign/CampaignService';


const useGetCurrentCampaigns = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getCurrentCampaigns'],
		queryFn: () => getCurrentCampaigns(axiosSecure),
	});
};

export default useGetCurrentCampaigns;
