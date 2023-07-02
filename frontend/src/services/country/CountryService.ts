import { baseUrl } from '../../constants/api';
import { Country, CreateCountry, GetCountry, UpdateCountry } from '../../types/CountryTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllCountries(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Country>>>(`${baseUrl}/countries`);
	return res.data;
}

export async function getCountryById(axiosSecure: AxiosInstance, getCountry: GetCountry) {
	const res = await axiosSecure.get<Response<Array<Country>>>(`${baseUrl}/countries/by-id`, {params: getCountry});
	return res.data;
}

export async function createCountry(axiosSecure: AxiosInstance, createCountry: CreateCountry) {
	const res = await axiosSecure.post<Response<Country>>(`${baseUrl}/countries/create`, createCountry);
	return res.data;
}

export async function editCountry(axiosSecure: AxiosInstance, editCountry: UpdateCountry) {
	const res = await axiosSecure.post<Response<Country>>(`${baseUrl}/countries/edit`, editCountry);
	return res.data;
}

