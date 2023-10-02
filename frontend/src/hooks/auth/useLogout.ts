import Cookies from 'js-cookie';

export const useLogout = () => {
	const logout = async () => {
		Cookies.remove('accessToken');
		Cookies.remove('refreshToken');
		// Wait a second
		await new Promise((resolve) => setTimeout(resolve, 1000));
		
		// const res = await logoutFn();
		return true;
		
		// return res;
	};

	return { logout };
};
