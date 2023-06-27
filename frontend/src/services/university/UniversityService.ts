import { AxiosInstance } from "axios";
import { baseUrl } from '../../constants/api';
import { University } from "../../types/UniversityTypes";
import { Response } from '../../types/ResponseTypes';
import { axiosSecure as axios } from '../axios';



export async function getUniversities() {
	const res = await axios.get<Response<Array<University>>>(`${baseUrl}/universities`);
	return res.data;
}