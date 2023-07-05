import { baseUrl } from '../../constants/api';
import { Campaign, CreateCampaignRequest, EditCampaignRequest, GetCampaignByIdRequest } from '../../types/CampaignTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllCampaigns(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Campaign>>>(`${baseUrl}/campaigns`);
	return res.data;
}

export async function getCampaignById(axiosSecure: AxiosInstance, getCampaignByIdRequest: GetCampaignByIdRequest) {
	const res = await axiosSecure.get<Response<Campaign>>(`${baseUrl}/campaigns/by-id`, {params: getCampaignByIdRequest});
	return res.data;
}

export async function createCampaign(axiosSecure: AxiosInstance, createCampaignRequest: CreateCampaignRequest) {
    const res = await axiosSecure.post<Response<Campaign>>(`${baseUrl}/campaigns/create`, {params: createCampaignRequest});
    return res.data;
}

export async function editCampaign(axiosSecure: AxiosInstance, editCampaignRequest: EditCampaignRequest) {
    const res = await axiosSecure.post<Response<Campaign>>(`${baseUrl}/campaigns/edit`, {params: editCampaignRequest});
    return res.data;
}