import { baseUrl } from '../../constants/api';
import { Announcement, CreateAnnouncement, EditAnnouncement } from '../../types/AnnouncementTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getUserAnnouncements(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Announcement>>>(`${baseUrl}/announcements`);
	return res.data;
}

export async function getAllAnnouncements(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Announcement>>>(`${baseUrl}/announcements/all`);
	return res.data;
}

export async function getAnnouncementById(axiosSecure: AxiosInstance, announcementId: number) {
    const res = await axiosSecure.get<Response<Array<Announcement>>>(`${baseUrl}/announcements/${announcementId}`);
    return res.data;
}

export async function createAnnouncement(axiosSecure: AxiosInstance, createAnnouncement: CreateAnnouncement) {
    const res = await axiosSecure.post<Response<Array<Announcement>>>(`${baseUrl}/announcements/create`, {params: createAnnouncement});
    return res.data;
}

export async function editAnnouncement(axiosSecure: AxiosInstance, announcementId: number, editAnnouncement: EditAnnouncement) {
    const res = await axiosSecure.post<Response<Array<Announcement>>>(`${baseUrl}/announcements/edit/${announcementId}`, editAnnouncement);
    return res.data;
}