import { AppShell } from '@mantine/core';
import { Outlet, useLocation } from 'react-router-dom';
import { PanelNavbar } from './components/menus/navbar/PanelNavbar';
import { AppHeader } from './components/header/AppHeader';
import { AdminPanelData } from './components/menus/navbar/AdminPanelData';
import { LeadPanelData } from './components/menus/navbar/LeadPanelData';
import { FacilitatorPanelData } from './components/menus/navbar/FacilitatorPanelData';
import useUserWithRole from './hooks/auth/useUserWithRole';
import useAxiosSecure from './hooks/auth/useAxiosSecure';
import { User, UserModel, UserType } from './types';



const Layout = () => {
	const { pathname } = useLocation();
	const axiosSecure = useAxiosSecure();

	const {
		data: currentUser,
		isLoading: isUserLoading,
		isError: isUserError,
	} = useUserWithRole(axiosSecure);
	
	let user: User = {
		id: 0,
		name: "Loading..",
		surname: "",
		email: "",
		username: "Loading..",
		userType: UserType.Lead,
		profileImage: "https://picsum.photos/200",
		phoneNumber: "",
		biography: "",
		interests: [],
		accessToken: "",
		refreshToken: "",
	}

	if (currentUser && currentUser.data) {
		user = currentUser.data.user;
	}
	
	const tabs = [
		{
			name: "Home",
			link: "/"
		},
		{
			name: "Map",
			link: "map",
		},
		{
			name: "User List",
			link: "user-list",
		},
		{
			name: "FAQ",
			link: "faq",
		}
	] 
	
	let header = <></>;
	let navbar = <></>;

	const checkLink = (link: string) => {
		return pathname.toLowerCase().startsWith(link);
	}

	if (checkLink("/panel/admin")) {
		navbar = <PanelNavbar panelName="Admin Panel" panelData={AdminPanelData}/>;
	} else if (checkLink("/panel/lead")) {
		navbar = <PanelNavbar panelName="Lead Panel" panelData={LeadPanelData} />;
	} else if (checkLink("/panel/facilitator")) {
		navbar = <PanelNavbar panelName='Facilitator Panel' panelData={FacilitatorPanelData}/>;
	} else {
		header = <AppHeader user={user} tabs={tabs} />;
	}

	
	return (
		<main className="App">
			<AppShell
				padding="md"
				header={header}
				navbar={navbar}
				styles={(theme) => ({
					main: {
						backgroundColor:
							theme.colorScheme === 'dark'
								? theme.colors.dark[8]
								: theme.colors.gray[0],
					},
				})}
			>
				<Outlet />
			</AppShell>
		</main>
	);
};

export default Layout;
