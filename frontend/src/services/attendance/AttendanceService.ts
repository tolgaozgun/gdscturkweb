import { baseUrl } from '../../constants/api';
import { Attendance, CreateAttendance, EditAttendance, LeadAttendance } from '../../types/AttendaceTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllAttendances(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Attendance>>>(`${baseUrl}/attendances`);
	return res.data;
}

export async function getBuddyTeamAttendancesByFacilitator(axiosSecure: AxiosInstance, facilitatorId: number) {
	const res = await axiosSecure.get<Response<Array<Attendance>>>(`${baseUrl}/attendances/facilitator/${facilitatorId}`);
	return res.data;
}

export async function getAttendanceListByCurrentLead(axiosSecure: AxiosInstance) {
    const res = await axiosSecure.get<Response<Array<LeadAttendance>>>(`${baseUrl}/attendances/lead/current`);
    return res.data;
}

export async function getAttendanceListByLeadId(axiosSecure: AxiosInstance, id: number) {
    const res = await axiosSecure.get<Response<Array<LeadAttendance>>>(`${baseUrl}/attendances/lead/${id}`);
    return res.data;
}

export async function getAttendanceById(axiosSecure: AxiosInstance, id: number) {
    const res = await axiosSecure.get<Response<Attendance>>(`${baseUrl}/attendances/${id}`);
    return res.data;
}

export async function createAttendance(axiosSecure: AxiosInstance, attendance: CreateAttendance) {
    const res = await axiosSecure.post<Response<Attendance>>(`${baseUrl}/attendances`, attendance);
    return res.data;
}

export async function editAttendance(axiosSecure: AxiosInstance, id: number, attendance: EditAttendance) {
    const res = await axiosSecure.put<Response<Attendance>>(`${baseUrl}/attendances/${id}`, attendance);
    return res.data;
}