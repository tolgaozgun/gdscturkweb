import { baseUrl } from '../../constants/api';
import { CreateUniversity, GetUniversity, University, UpdateUniversity } from "../../types/UniversityTypes";
import { Response } from '../../types/ResponseTypes';
import { axiosSecure as axios } from '../axios';
import { AxiosInstance } from 'axios';

export async function getUniversities() {
	const res = await axios.get<Response<Array<University>>>(`${baseUrl}/universities`);
	return res.data;
}

export async function getUniversityById(axiosSecure: AxiosInstance, getUniversity: GetUniversity) {
	const res = await axiosSecure.get<Response<Array<University>>>(`${baseUrl}/universities/by-id`, {params: getUniversity});
	return res.data;
}

export async function createUniversity(axiosSecure: AxiosInstance, createUniversity: CreateUniversity) {
	const res = await axiosSecure.post<Response<University>>(`${baseUrl}/universities/create`, createUniversity);
	return res.data;
}

export async function editUniversity(axiosSecure: AxiosInstance, editUniversity: UpdateUniversity) {
	const res = await axiosSecure.post<Response<University>>(`${baseUrl}/universities/edit`, editUniversity);
	return res.data;
}