import Cookies from 'js-cookie';
import { User } from '../../types/UserTypes';

export const useUser = () => {
	const currentUser = Cookies.get('currentUser');
	if (currentUser) {
		return JSON.parse(currentUser) as User;
	}

	return null;
};
