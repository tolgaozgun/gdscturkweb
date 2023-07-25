import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCampaignPagesByCampaignId } from '../../services/campaign/CampaignService';


const useGetCampaignPagesByCampaignId = (axiosSecure: AxiosInstance, campaignId: number) => {
	return useQuery({
		queryKey: ['getCampaignPagesByCampaignId'],
		queryFn: () => getCampaignPagesByCampaignId(axiosSecure, campaignId),
	});
};

export default useGetCampaignPagesByCampaignId;
