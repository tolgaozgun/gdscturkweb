import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Layout from './layout.tsx';
import LoginPage from './pages/auth/LoginPage.tsx';
import RegisterPage from './pages/auth/RegisterPage.tsx';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import ProviderWrapper from './components/common/other/ProviderWrapper.tsx';
import MapPage from './pages/MapPage.tsx';
import UserListPage from './pages/UserListPage.tsx';
import LoadingPage from './pages/LoadingPage.tsx';
import PanelDashboardPage from './pages/panel/PanelDashboardPage.tsx';
import { ContextMenuProvider } from 'mantine-contextmenu';
import PanelUserListPage from './pages/panel/PanelUserListPage.tsx';


const router = createBrowserRouter([
	{
		children: [
			{
				path: '/',
				element: <Layout />,
				children: [
					{
						path: '',
						element: <RegisterPage />,
					},
					{
						path: '/login',
						element: <LoginPage />,
					},
					{
						path: '/register',
						element: <RegisterPage />,
					},
					{
						path: '/map',
						element: <MapPage />
					},
					{
						path: '/user-list',
						element: <UserListPage />
					},
					{
						path: '/loading',
						element: <LoadingPage />
					},
					{
						path: '/panel',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/dashboard',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/users/list',
						element: <PanelUserListPage />
					},
					{
						path: '/panel/users/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/buddy-teams/list',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/buddy-teams/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/campaigns/list',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/campaigns/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/universities/list',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/universities/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/cities/list',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/cities/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/countries/list',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/countries/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/settings',
						element: <PanelDashboardPage />
					},
				],
			},
		],
	},
]);
const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
		<QueryClientProvider client={queryClient}>
			<ContextMenuProvider>
				<ProviderWrapper>
					<RouterProvider router={router} />
				</ProviderWrapper>
			</ContextMenuProvider>
		</QueryClientProvider>
	</React.StrictMode>,
);
