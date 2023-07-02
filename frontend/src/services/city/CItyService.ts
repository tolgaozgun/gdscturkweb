import { baseUrl } from '../../constants/api';
import { City, CreateCity, GetCity, UpdateCity } from '../../types/CityTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllCities(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<City>>>(`${baseUrl}/cities`);
	return res.data;
}

export async function getCityById(axiosSecure: AxiosInstance, getCity: GetCity) {
	const res = await axiosSecure.get<Response<Array<City>>>(`${baseUrl}/cities/by-id`, {params: getCity});
	return res.data;
}

export async function createCity(axiosSecure: AxiosInstance, createCity: CreateCity) {
	const res = await axiosSecure.post<Response<City>>(`${baseUrl}/cities/create`, createCity);
	return res.data;
}

export async function editCity(axiosSecure: AxiosInstance, editCity: UpdateCity) {
	const res = await axiosSecure.post<Response<City>>(`${baseUrl}/cities/edit`, editCity);
	return res.data;
}