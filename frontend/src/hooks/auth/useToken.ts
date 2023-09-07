import Cookies from 'js-cookie';
import { Token } from '../../types';

export const useToken = () => {
	const token = Cookies.get('token');
	if (token) {
		return JSON.parse(token) as Token;
	}

	return null;
};