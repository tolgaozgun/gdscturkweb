import { baseUrl } from '../../constants/api';
import { Activity } from '../../types/EventTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllEvents(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Activity>>>(`${baseUrl}/events`);
	return res.data;
}

export async function getEventsByUniversityId(universityId: number, axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Activity>>>(`${baseUrl}/events/university/${universityId}`);
	return res.data;
}

export async function getEventsByCurrentUserUniversity(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Activity>>>(`${baseUrl}/events/university/current-user`);
	return res.data;
}

export async function getEventsByCurrentUserBuddy(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Activity>>>(`${baseUrl}/events/buddy-team/current-user`);
	return res.data;
}

export async function parseCurrentUserUniversityEvents(axiosSecure: AxiosInstance) {
    const res = await axiosSecure.post<Response<Array<Activity>>>(`${baseUrl}/events/parse/current-university`);
    return res.data;
}

export async function parseUniversityEventsByUniversityId(universityId: number, axiosSecure: AxiosInstance) {
    const res = await axiosSecure.post<Response<Array<Activity>>>(`${baseUrl}/events/parse/${universityId}`);
    return res.data;
}
