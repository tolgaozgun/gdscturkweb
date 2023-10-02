import { baseUrl } from '../../constants/api';
import { CreateUniversity, University, UpdateUniversity } from "../../types/UniversityTypes";
import { Response } from '../../types/ResponseTypes';
import axios from 'axios';
import { AxiosInstance } from 'axios';

export async function getUniversities() {
	const res = await axios.get<Response<Array<University>>>(`${baseUrl}/universities`);
	return res.data;
}

export async function getUniversitiesAuthed(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<University>>>(`${baseUrl}/universities`);
	return res.data;
}

export async function getUniversityById(axiosSecure: AxiosInstance, universityId: number) {
	const res = await axiosSecure.get<Response<Array<University>>>(`${baseUrl}/universities/${universityId}`);
	return res.data;
}

export async function createUniversity(axiosSecure: AxiosInstance, createUniversity: CreateUniversity) {
	const res = await axiosSecure.post<Response<University>>(`${baseUrl}/universities/create`, createUniversity);
	return res.data;
}

export async function editUniversity(axiosSecure: AxiosInstance, universityId: number, editUniversity: UpdateUniversity) {
	const res = await axiosSecure.post<Response<University>>(`${baseUrl}/universities/edit/${universityId}`, editUniversity);
	return res.data;
}