import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCampaignById } from '../../services/campaign/CampaignService';


const useGetCampaignById = (axiosSecure: AxiosInstance, campaignId: number) => {
	return useQuery({
		queryKey: ['getCampaignById'],
		queryFn: () => getCampaignById(axiosSecure, campaignId),
	});
};

export default useGetCampaignById;
