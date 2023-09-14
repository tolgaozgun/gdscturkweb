import { logout as logoutFn } from '../../services/auth';
import { useUser } from '../../contexts/UserContext';

export const useLogout = () => {
	const logout = async () => {
		const {dispatch} = useUser();
		logoutFn();
		dispatch({type: 'LOGOUT'});
	};

	return { logout };
};
