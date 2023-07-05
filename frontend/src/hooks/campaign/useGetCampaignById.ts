import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getCampaignById } from '../../services/campaign/CampaignService';
import { GetCampaignByIdRequest } from '../../types/CampaignTypes';


const useGetCampaignById = (axiosSecure: AxiosInstance, getCampaignByIdRequest: GetCampaignByIdRequest) => {
	return useQuery({
		queryKey: ['getCampaignById'],
		queryFn: () => getCampaignById(axiosSecure, getCampaignByIdRequest),
	});
};

export default useGetCampaignById;
