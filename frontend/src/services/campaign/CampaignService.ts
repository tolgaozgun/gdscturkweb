import { baseUrl } from '../../constants/api';
import { Campaign, CampaignPage, CreateCampaignRequest, EditCampaignRequest } from '../../types/CampaignTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllCampaigns(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Campaign>>>(`${baseUrl}/campaigns`);
	return res.data;
}

export async function getCurrentCampaigns(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Campaign>>>(`${baseUrl}/campaigns/current`);
	return res.data;
}

export async function getCampaignById(axiosSecure: AxiosInstance, campaignId: number) {
	const res = await axiosSecure.get<Response<Campaign>>(`${baseUrl}/campaigns/${campaignId}`);
	return res.data;
}

export async function createCampaign(axiosSecure: AxiosInstance, createCampaignRequest: CreateCampaignRequest) {
    const res = await axiosSecure.post<Response<Campaign>>(`${baseUrl}/campaigns/create`, {params: createCampaignRequest});
    return res.data;
}

export async function editCampaign(axiosSecure: AxiosInstance, campaignId: number, editCampaignRequest: EditCampaignRequest) {
    const res = await axiosSecure.post<Response<Campaign>>(`${baseUrl}/campaigns/edit/${campaignId}`, editCampaignRequest);
    return res.data;
}

export async function getCampaignPagesByCampaignId(axiosSecure: AxiosInstance, campaignId: number) {
	const res = await axiosSecure.get<Response<Array<CampaignPage>>>(`${baseUrl}/campaigns/pages/by-campaign/${campaignId}`);
	return res.data;
}

export async function getCampaignPageById(axiosSecure: AxiosInstance, campaignPageId: number) {
	const res = await axiosSecure.get<Response<CampaignPage>>(`${baseUrl}/campaigns/pages/${campaignPageId}`);
	return res.data;
}