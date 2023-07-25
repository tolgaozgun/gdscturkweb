import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { editCampaign } from '../../services/campaign/CampaignService';
import { EditCampaignRequest } from '../../types/CampaignTypes';

const useEditCampaign = (axiosSecure: AxiosInstance, campaignId: number, editCampaignRequest: EditCampaignRequest) => {
	return useQuery({
		queryKey: ['editCampaign'],
		queryFn: () => editCampaign(axiosSecure, campaignId, editCampaignRequest),
	});
};

export default useEditCampaign;
