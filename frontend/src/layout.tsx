import { AppShell, Container, MediaQuery, Burger } from '@mantine/core';
import { Outlet, useLocation } from 'react-router-dom';
import { PanelNavbar } from './components/menus/navbar/PanelNavbar';
import { AppHeader } from './components/header/AppHeader';
import { AdminPanelData } from './components/menus/navbar/AdminPanelData';
import { LeadPanelData } from './components/menus/navbar/LeadPanelData';
import { FacilitatorPanelData } from './components/menus/navbar/FacilitatorPanelData';
import { useUser } from './contexts/UserContext';
import { CoreTeamPanelData } from './components/menus/navbar/CoreTeamPanelData';
import { PanelHeader } from './components/header/PanelHeader';
import { useState } from 'react';

export const Layout = () => {
	const { pathname } = useLocation();
	let {user, isUserLoading, isUserError} = useUser();

	const [opened, setOpened] = useState(false);

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
			name: "About",
			link: "/about"
		},
		{
			name: "Contact",
			link: "/contact"
		},
		// {
		// 	name: "Map",
		// 	link: "map",
		// },
		// {
		// 	name: "User List",
		// 	link: "user-list",
		// },
		// {
		// 	name: "FAQ",
		// 	link: "faq",
		// }
	];

	let header = <></>;
	let navbar = <></>;

	const checkLink = (link: string) => {
		return pathname.toLowerCase().startsWith(link);
	};

	if (checkLink("/panel/admin")) {
		navbar = <PanelNavbar hidden={!opened} panelName="Admin Panel" panelData={AdminPanelData} />;
		header = <PanelHeader 

			burger={
				<MediaQuery largerThan="md" styles={{ display: 'none' }}>
					<Burger
						opened={opened}
						onClick={() => setOpened(o => !o)}
						size="sm"
						mr="xl"
					/>
				</MediaQuery>
			}
		 />
	} else if (checkLink("/panel/lead")) {
		navbar = <PanelNavbar hidden={!opened} panelName="Lead Panel" panelData={LeadPanelData} />;
		header = <PanelHeader 

			burger={
				<MediaQuery largerThan="md" styles={{ display: 'none' }}>
					<Burger
						opened={opened}
						onClick={() => setOpened(o => !o)}
						size="sm"
						mr="xl"
					/>
				</MediaQuery>
			}
		 />
	} else if (checkLink("/panel/facilitator")) {
		navbar = <PanelNavbar hidden={!opened} panelName='Facilitator Panel' panelData={FacilitatorPanelData} />;
		header = <PanelHeader 

			burger={
				<MediaQuery largerThan="md" styles={{ display: 'none' }}>
					<Burger
						opened={opened}
						onClick={() => setOpened(o => !o)}
						size="sm"
						mr="xl"
					/>
				</MediaQuery>
			}
		 />
	} else if (checkLink("/panel/core-team")) {
		navbar = <PanelNavbar hidden={!opened} panelName='Core Team Panel' panelData={CoreTeamPanelData} />;
		header = <PanelHeader 

			burger={
				<MediaQuery largerThan="md" styles={{ display: 'none' }}>
					<Burger
						opened={opened}
						onClick={() => setOpened(o => !o)}
						size="sm"
						mr="xl"
					/>
				</MediaQuery>
			}
		 />
	} else if(!checkLink("/login") && !checkLink("/register")) {
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