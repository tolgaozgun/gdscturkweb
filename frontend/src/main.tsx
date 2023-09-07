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
import AdminDashboardPage from './pages/panel/admin/AdminDashboardPage.tsx';
import { ContextMenuProvider } from 'mantine-contextmenu';
import PanelUserListPage from './pages/panel/admin/PanelUserListPage.tsx';
import { FaqPage } from './pages/faq/FaqPage.tsx';
import PanelQuestionsListPage from './pages/panel/admin/question/PanelQuestionsListPage.tsx';
import CampaignPage from './pages/CampaignDisplayPage.tsx';
import PanelBuddyTeamListPage from './pages/panel/admin/PanelBuddyTeamListPage.tsx';
import PanelUniversityListPage from './pages/panel/admin/university/PanelUniversityListPage.tsx';
import PanelCityListPage from './pages/panel/admin/city/PanelCityListPage.tsx';
import PanelCountryListPage from './pages/panel/admin/country/PanelCountryListPage.tsx';
import PanelCampaignListPage from './pages/panel/admin/campaign/PanelCampaignListPage.tsx';
import PanelQuestionCategoriesListPage from './pages/panel/admin/question/PanelQuestionCategoriesListPage.tsx';
import AddCityPage from './pages/panel/admin/city/AddCityPage.tsx';
import AddCountryPage from './pages/panel/admin/country/AddCountryPage.tsx';
import AddUniversityPage from './pages/panel/admin/university/AddUniversityPage.tsx';
import PanelAddQuestionPage from './pages/panel/admin/question/PanelAddQuestionPage.tsx';
import MainPage from './pages/MainPage.tsx';
import NotFoundPage from './pages/NotFoundPage.tsx';
import LeadDashboardPage from './pages/panel/lead/LeadDashboardPage.tsx';
import FacilitatorDashboardPage from './pages/panel/facilitator/FacilitatorDashboardPage.tsx';
import LeadPanelAttendancePage from './pages/panel/lead/LeadPanelAttendancePage.tsx';
import FacilitatorPanelAttendancePage from './pages/panel/facilitator/buddy-team/FacilitatorPanelAttendancePage.tsx';
import AddAttendancePage from './pages/panel/facilitator/buddy-team/AddAttendancePage.tsx';
import EditBuddyTeamForm from './components/forms/panel/buddy-team/EditBuddyTeamForm.tsx';
import CreateFacilitatorPage from './pages/panel/admin/facilitator/CreateFacilitatorPage.tsx';
import CreateCoreTeamPage from './pages/panel/admin/core-team/CreateCoreTeamPage.tsx';
import CreateLeadPage from './pages/panel/admin/lead/CreateLeadPage.tsx';


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
						element: <AdminDashboardPage />
					},
					{
						path: '/campaign/:campaignId',
						element: <CampaignPage />
					},
					{
						path: '/panel/admin/dashboard',
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/admin/users/list',
						element: <PanelUserListPage />
					},
					{
						path: '/panel/admin/users/create-lead',
						element: <CreateLeadPage />
					},
					{
						path: '/panel/admin/users/create-core-team-member',
						element: <CreateCoreTeamPage />
					},
					{
						path: '/panel/admin/users/create-facilitator',
						element: <CreateFacilitatorPage />
					},
					{
						path: '/panel/admin/users/verification',
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/admin/buddy-teams/list',
						element: <PanelBuddyTeamListPage />
					},
					{
						path: '/panel/admin/buddy-teams/add',
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/admin/campaigns/list',
						element: <PanelCampaignListPage />
					},
					{
						path: '/panel/admin/campaigns/add',
						element: <AdminDashboardPage />
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
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/admin/settings',
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/facilitator',
						element: <FacilitatorDashboardPage />
					},
					{
						path: '/panel/facilitator/dashboard',
						element: <FacilitatorDashboardPage />
					},
					{
						path: '/panel/facilitator/buddy-teams/my',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/buddy-teams/my/edit',
						element: <EditBuddyTeamForm />
					},
					{
						path: '/panel/facilitator/buddy-teams/attendance',
						element: <FacilitatorPanelAttendancePage />
					},
					{
						path: '/panel/facilitator/buddy-teams/attendance/add',
						element: <AddAttendancePage />
					},
					{
						path: '/panel/facilitator/buddy-teams/all',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/campaigns/current',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/campaigns/all',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/universities/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/cities/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/countries/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/questions/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/questions/ask',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/settings',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead',
						element: <LeadDashboardPage />
					},
					{
						path: '/panel/lead/dashboard',
						element: <LeadDashboardPage />
					},
					{
						path: '/panel/lead/core-team/my',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/core-team/invite',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/buddy-teams/my',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/buddy-teams/attendance',
						element: <LeadPanelAttendancePage />
					},
					{
						path: '/panel/lead/buddy-teams/all',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/campaigns/current',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/campaigns/all',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/universities/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/cities/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/countries/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/questions/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/questions/ask',
						element: <NotFoundPage />
					},
					{
						path: '/panel/lead/settings',
						element: <NotFoundPage />
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


const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
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
