import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { createCampaign } from '../../services/campaign/CampaignService';
import { CreateCampaignRequest } from '../../types/CampaignTypes';


const useCreateCampaign = (axiosSecure: AxiosInstance, createCampaignRequest: CreateCampaignRequest) => {
	return useQuery({
		queryKey: ['createCampaign'],
		queryFn: () => createCampaign(axiosSecure, createCampaignRequest),
	});
};

export default useCreateCampaign;
