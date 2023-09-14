import Cookies from 'js-cookie';
import { login as loginFn } from '../../services/auth';
import { isErrorResponse } from '../../utils/utils';
import { useUser } from '../../contexts/UserContext';

export const useLogin = () => {
	const login = async (email: string, password: string) => {
		const res = await loginFn(email, password);
		const {user, dispatch} = useUser();

		// Return without setting cookies if login failed
		if (isErrorResponse(res)) {
			return res;
		}

		dispatch({type: 'LOGIN', payload: res.data});

		// let token = {
		// 	accessToken: res.data.accessToken,
		// 	refreshToken: res.data.refreshToken,
		// }

		// // Set the cookies and return the user
		// Cookies.set('token', JSON.stringify(token));
		return res;
	};

	return { login };
};
