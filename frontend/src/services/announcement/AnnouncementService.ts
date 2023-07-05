import { baseUrl } from '../../constants/api';
import { CreateAnnouncement, EditAnnouncement, GetAnnouncement } from '../../types/AnnouncementTypes';
import { BuddyTeam } from '../../types/BuddyTeamTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getUserAnnouncements(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements`);
	return res.data;
}

export async function getAllAnnouncements(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/all`);
	return res.data;
}

export async function getAnnouncementById(axiosSecure: AxiosInstance, getAnnouncement: GetAnnouncement) {
    const res = await axiosSecure.get<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/by-id`, {params: getAnnouncement});
    return res.data;
}

export async function createAnnouncement(axiosSecure: AxiosInstance, createAnnouncement: CreateAnnouncement) {
    const res = await axiosSecure.post<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/create`, {params: createAnnouncement});
    return res.data;
}

export async function editAnnouncement(axiosSecure: AxiosInstance, editAnnouncement: EditAnnouncement) {
    const res = await axiosSecure.post<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/edit`, {params: editAnnouncement});
    return res.data;
}