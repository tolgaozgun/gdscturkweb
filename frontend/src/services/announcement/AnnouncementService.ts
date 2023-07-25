import { baseUrl } from '../../constants/api';
import { CreateAnnouncement, EditAnnouncement } from '../../types/AnnouncementTypes';
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

export async function getAnnouncementById(axiosSecure: AxiosInstance, announcementId: number) {
    const res = await axiosSecure.get<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/${announcementId}`);
    return res.data;
}

export async function createAnnouncement(axiosSecure: AxiosInstance, createAnnouncement: CreateAnnouncement) {
    const res = await axiosSecure.post<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/create`, {params: createAnnouncement});
    return res.data;
}

export async function editAnnouncement(axiosSecure: AxiosInstance, announcementId: number, editAnnouncement: EditAnnouncement) {
    const res = await axiosSecure.post<Response<Array<BuddyTeam>>>(`${baseUrl}/announcements/edit/${announcementId}`, editAnnouncement);
    return res.data;
}