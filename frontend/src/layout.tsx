import { AppShell } from '@mantine/core';
import { Outlet, useLocation } from 'react-router-dom';
import { PanelNavbar } from './components/menus/navbar/PanelNavbar';
import { AppHeader } from './components/header/AppHeader';
import { AdminPanelData } from './components/menus/navbar/AdminPanelData';
import { LeadPanelData } from './components/menus/navbar/LeadPanelData';
import { FacilitatorPanelData } from './components/menus/navbar/FacilitatorPanelData';
import { useUser } from './contexts/UserContext';
import { CoreTeamPanelData } from './components/menus/navbar/CoreTeamPanelData';

export const Layout = () => {
	const { pathname } = useLocation();
	let {user, isUserLoading, isUserError} = useUser();

	// const {
	// 	data: currentUser,
	// 	isLoading: isUserLoading,
	// 	isError: isUserError,
	// } = useUserWithRole(axiosSecure);

	// if (!user) {
	// 	user = {
	// 		id: 0,
	// 		name: "Not logged in!",
	// 		surname: "",
	// 		email: "",
	// 		username: "Loading..",
	// 		userType: UserType.Lead,
	// 		profileImage: "https://picsum.photos/200",
	// 		phoneNumber: "",
	// 		biography: "",
	// 		interests: [],
	// 		accessToken: "",
	// 		refreshToken: "",
	// 	}
	// 	isLoggedIn = false;
	// }
	// if (currentUser && currentUser.data) {
	// 	user = currentUser.data.user;
	// 	isLoggedIn = true;
	// }
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
	];

	let header = <></>;
	let navbar = <></>;

	const checkLink = (link: string) => {
		return pathname.toLowerCase().startsWith(link);
	};

	if (checkLink("/panel/admin")) {
		navbar = <PanelNavbar panelName="Admin Panel" panelData={AdminPanelData} />;
	} else if (checkLink("/panel/lead")) {
		navbar = <PanelNavbar panelName="Lead Panel" panelData={LeadPanelData} />;
	} else if (checkLink("/panel/facilitator")) {
		navbar = <PanelNavbar panelName='Facilitator Panel' panelData={FacilitatorPanelData} />;
	} else if (checkLink("/panel/core-team")) {
		navbar = <PanelNavbar panelName='Core Team Panel' panelData={CoreTeamPanelData} />;
	}else {
		header = <AppHeader user={user} tabs={tabs} isLoggedIn={!isUserError || !isUserLoading} />;
	}


	return (
		<main className="App">
			<AppShell
				padding="md"
				header={header}
				navbar={navbar}
				styles={(theme) => ({
					main: {
						backgroundColor: theme.colorScheme === 'dark'
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