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
import PanelDashboardPage from './pages/admin/PanelDashboardPage.tsx';
import { ContextMenuProvider } from 'mantine-contextmenu';
import PanelUserListPage from './pages/admin/PanelUserListPage.tsx';
import { FaqPage } from './pages/faq/FaqPage.tsx';
import PanelQuestionsListPage from './pages/admin/question/PanelQuestionsListPage.tsx';
import CampaignPage from './pages/CampaignDisplayPage.tsx';
import PanelBuddyTeamListPage from './pages/admin/PanelBuddyTeamListPage.tsx';
import PanelUniversityListPage from './pages/admin/university/PanelUniversityListPage.tsx';
import PanelCityListPage from './pages/admin/city/PanelCityListPage.tsx';
import PanelCountryListPage from './pages/admin/country/PanelCountryListPage.tsx';
import PanelCampaignListPage from './pages/admin/campaign/PanelCampaignListPage.tsx';
import PanelQuestionCategoriesListPage from './pages/admin/question/PanelQuestionCategoriesListPage.tsx';
import AddCityPage from './pages/admin/city/AddCityPage.tsx';
import AddCountryPage from './pages/admin/country/AddCountryPage.tsx';
import AddUniversityPage from './pages/admin/university/AddUniversityPage.tsx';
import PanelAddQuestionPage from './pages/admin/question/PanelAddQuestionPage.tsx';
import MainPage from './pages/MainPage.tsx';
import NotFoundPage from './pages/NotFoundPage.tsx';


const router = createBrowserRouter([
	{
		children: [
			{
				path: '/',
				element: <Layout />,
				children: [
					{
						path: '',
						element: <MainPage />,
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
						path: '/faq',
						element: <FaqPage />
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
						path: '/panel/admin',
						element: <PanelDashboardPage />
					},
					{
						path: '/campaign/:campaignId',
						element: <CampaignPage />
					},
					{
						path: '/panel/admin/dashboard',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/users/list',
						element: <PanelUserListPage />
					},
					{
						path: '/panel/admin/users/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/users/verification',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/buddy-teams/list',
						element: <PanelBuddyTeamListPage />
					},
					{
						path: '/panel/admin/buddy-teams/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/campaigns/list',
						element: <PanelCampaignListPage />
					},
					{
						path: '/panel/admin/campaigns/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/universities/list',
						element: <PanelUniversityListPage />
					},
					{
						path: '/panel/admin/universities/add',
						element: <AddUniversityPage />
					},
					{
						path: '/panel/admin/cities/list',
						element: <PanelCityListPage />
					},
					{
						path: '/panel/admin/cities/add',
						element: <AddCityPage />
					},
					{
						path: '/panel/admin/countries/list',
						element: <PanelCountryListPage />
					},
					{
						path: '/panel/admin/countries/add',
						element: <AddCountryPage />
					},
					{
						path: '/panel/admin/questions/list',
						element: <PanelQuestionsListPage />
					},
					{
						path: '/panel/admin/questions/add',
						element: <PanelAddQuestionPage />
					},
					{
						path: '/panel/admin/questions/categories/list',
						element: <PanelQuestionCategoriesListPage />
					},
					{
						path: '/panel/admin/questions/categories/add',
						element: <PanelDashboardPage />
					},
					{
						path: '/panel/admin/settings',
						element: <PanelDashboardPage />
					},
					{
						path: '*',
						element: <NotFoundPage />
					}
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
