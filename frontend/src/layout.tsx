import { AppShell } from '@mantine/core';
import { Outlet, useLocation } from 'react-router-dom';
import { AdminPanelNavbar } from './components/menus/navbar/AdminPanelNavbar';
import { SecondHeader } from './components/header/SecondHeader';


const Layout = () => {
	const { pathname } = useLocation();

	const user = {
		name: "Tolga Ozgun",
		image: "https://picsum.photos/200"
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
			name: "Loading",
			link: "loading",
		}
	] 
	
	// user: { name: string; image: string };
	// tabs: { name: string; link: string }[];

	return (
		<main className="App">
			<AppShell
				padding="md"
				header={pathname.toLowerCase().startsWith("/panel") ? <></> : <SecondHeader user={user} tabs={tabs} />}
				navbar={pathname.toLowerCase().startsWith("/panel") ? <AdminPanelNavbar/> : <></>}
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
