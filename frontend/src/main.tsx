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
import AdminPanelUniversityListPage from './pages/panel/admin/university/AdminPanelUniversityListPage.tsx';
import AdminPanelCityListPage from './pages/panel/admin/city/AdminPanelCityListPage.tsx';
import AdminPanelCountryListPage from './pages/panel/admin/country/AdminPanelCountryListPage.tsx';
import AdminPanelCampaignListPage from './pages/panel/admin/campaign/AdminPanelCampaignListPage.tsx';
import PanelQuestionCategoriesListPage from './pages/panel/admin/question/PanelQuestionCategoriesListPage.tsx';
import AddCityPage from './pages/panel/admin/city/AdminPanelAddCityPage.tsx';
import AdminPanelAddCountryPage from './pages/panel/admin/country/AdminPanelAddCountryPage.tsx';
import AdminPanelAddUniversityPage from './pages/panel/admin/university/AdminPanelAddUniversityPage.tsx';
import PanelAddQuestionPage from './pages/panel/admin/question/PanelAddQuestionPage.tsx';
import MainPage from './pages/MainPage.tsx';
import NotFoundPage from './pages/NotFoundPage.tsx';
import LeadDashboardPage from './pages/panel/lead/LeadDashboardPage.tsx';
import FacilitatorDashboardPage from './pages/panel/facilitator/FacilitatorDashboardPage.tsx';
import LeadPanelAttendancePage from './pages/panel/lead/LeadPanelAttendancePage.tsx';
import FacilitatorPanelAttendancePage from './pages/panel/facilitator/buddy-team/FacilitatorPanelAttendancePage.tsx';
import FacilitatorPanelAddAttendancePage from './pages/panel/facilitator/buddy-team/FacilitatorPanelAddAttendancePage.tsx';
import EditBuddyTeamForm from './components/forms/panel/buddy-team/EditBuddyTeamForm.tsx';
import CreateFacilitatorPage from './pages/panel/admin/facilitator/CreateFacilitatorPage.tsx';
import CreateCoreTeamPage from './pages/panel/admin/core-team/CreateCoreTeamPage.tsx';
import CreateLeadPage from './pages/panel/admin/lead/CreateLeadPage.tsx';
import { UserProvider } from './contexts/UserContext.tsx';
import LeadPanelCampaignListPage from './pages/panel/lead/campaign/LeadPanelCampaignListPage.tsx';
import LeadPanelUniversityListPage from './pages/panel/lead/university/LeadPanelUniversityListPage.tsx';
import LeadPanelCityListPage from './pages/panel/lead/city/LeadPanelCityListPage.tsx';
import LeadPanelCountryListPage from './pages/panel/lead/country/LeadPanelCountryListPage.tsx';
import LeadPanelCurrentCampaignListPage from './pages/panel/lead/campaign/LeadPanelCurrentCampaignListPage.tsx';
import FacilitatorPanelUniversityListPage from './pages/panel/facilitator/university/FacilitatorPanelUniversityListPage.tsx';
import FacilitatorPanelCityListPage from './pages/panel/facilitator/city/FacilitatorPanelCityListPage.tsx';
import FacilitatorPanelCountryListPage from './pages/panel/facilitator/country/FacilitatorPanelCountryListPage.tsx';
import FacilitatorPanelCampaignListPage from './pages/panel/facilitator/campaign/FacilitatorPanelCampaignListPage.tsx';
import FacilitatorPanelCurrentCampaignListPage from './pages/panel/facilitator/campaign/FacilitatorPanelCurrentCampaignListPage.tsx';
import SendEmailVerificationPage from './pages/auth/SendEmailVerificationPage.tsx';
import EmailVerifyPage from './pages/auth/EmailVerifyPage.tsx';
import LeadPanelBuddyTeamPage from './pages/panel/lead/LeadPanelBuddyTeamPage.tsx';
import LeadPanelInviteCoreTeam from './pages/panel/lead/core-team/LeadPanelInviteCoreTeam.tsx';
import LeadPanelCoreTeamPage from './pages/panel/lead/core-team/LeadPanelCoreTeamPage.tsx';
import LeadPanelUserSettings from './pages/panel/lead/LeadPanelUserSettings.tsx';
import LogoutPage from './pages/LogoutPage.tsx';
import CoreTeamPanelCoreTeamPage from './pages/panel/core-team/core-team/CoreTeamPanelCoreTeamPage.tsx';
import CoreTeamPanelCurrentCampaignListPage from './pages/panel/core-team/campaign/CoreTeamPanelCurrentCampaignListPage.tsx';
import CoreTeamPanelCampaignListPage from './pages/panel/core-team/campaign/CoreTeamPanelCampaignListPage.tsx';
import CoreTeamPanelUniversityListPage from './pages/panel/core-team/university/CoreTeamPanelUniversityListPage.tsx';
import CoreTeamPanelCityListPage from './pages/panel/core-team/city/CoreTeamPanelCityListPage.tsx';
import CoreTeamPanelAccountSettings from './pages/panel/core-team/CoreTeamPanelAccountSettings.tsx';
import CoreTeamPanelCountryListPage from './pages/panel/core-team/country/CoreTeamPanelCountryListPage.tsx';
import CoreTeamDashboardPage from './pages/panel/core-team/CoreTeamDashboardPage.tsx';
import LeadPanelMyEventsPage from './pages/panel/lead/event/LeadPanelMyEventsPage.tsx';
import LeadPanelAllEventsPage from './pages/panel/lead/event/LeadPanelAllEventsPage.tsx';
import LeadPanelAllBuddyTeamsPage from './pages/panel/lead/LeadPanelAllBuddyTeamsPage.tsx';
import LeadPanelLeadListPage from './pages/panel/lead/LeadPanelLeadListPage.tsx';
import LeadPanelLeadSettings from './pages/panel/lead/LeadPanelLeadSettings.tsx';


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
						path: '/logout',
						element: <LogoutPage />,
					},
					{
						path: '/register',
						element: <RegisterPage />,
					},
					{
						path: '/verify-email',
						element: <SendEmailVerificationPage />,
					},
					{
						path: '/verify-email/step-2/',
						element: <EmailVerifyPage />
					},
					// {
					// 	path: '/map',
						// element: <MapPage />
					// },
					// {
					// 	path: '/faq',
					// 	element: <FaqPage />
					// },
					// {
					// 	path: '/user-list',
					// 	element: <UserListPage />
					// },
					// {
					// 	path: '/loading',
					// 	element: <LoadingPage />
					// },
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
						element: <AdminPanelCampaignListPage />
					},
					{
						path: '/panel/admin/campaigns/add',
						element: <AdminDashboardPage />
					},
					{
						path: '/panel/admin/universities/list',
						element: <AdminPanelUniversityListPage />
					},
					{
						path: '/panel/admin/universities/add',
						element: <AdminPanelAddUniversityPage />
					},
					{
						path: '/panel/admin/cities/list',
						element: <AdminPanelCityListPage />
					},
					{
						path: '/panel/admin/cities/add',
						element: <AddCityPage />
					},
					{
						path: '/panel/admin/countries/list',
						element: <AdminPanelCountryListPage />
					},
					{
						path: '/panel/admin/countries/add',
						element: <AdminPanelAddCountryPage />
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
						element: <FacilitatorPanelAddAttendancePage />
					},
					{
						path: '/panel/facilitator/buddy-teams/all',
						element: <NotFoundPage />
					},
					{
						path: '/panel/facilitator/campaigns/current',
						element: <FacilitatorPanelCurrentCampaignListPage />
					},
					{
						path: '/panel/facilitator/campaigns/all',
						element: <FacilitatorPanelCampaignListPage />
					},
					{
						path: '/panel/facilitator/universities/list',
						element: <FacilitatorPanelUniversityListPage />
					},
					{
						path: '/panel/facilitator/cities/list',
						element: <FacilitatorPanelCityListPage />
					},
					{
						path: '/panel/facilitator/countries/list',
						element: <FacilitatorPanelCountryListPage />
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
						element: <LeadPanelCoreTeamPage />
					},
					{
						path: '/panel/lead/core-team/invite',
						element: <LeadPanelInviteCoreTeam />
					},
					{
						path: '/panel/lead/buddy-teams/my',
						element: <LeadPanelBuddyTeamPage />
					},
					{
						path: '/panel/lead/buddy-teams/attendance',
						element: <LeadPanelAttendancePage />
					},
					{
						path: '/panel/lead/buddy-teams/all',
						element: <LeadPanelAllBuddyTeamsPage />
					},
					{
						path: '/panel/lead/leads/all',
						element: <LeadPanelLeadListPage />
					},
					{
						path: '/panel/lead/events/my',
						element: <LeadPanelMyEventsPage />
					},
					{
						path: '/panel/lead/events/all',
						element: <LeadPanelAllEventsPage />
					},
					{
						path: '/panel/lead/campaigns/current',
						element: <LeadPanelCurrentCampaignListPage />
					},
					{
						path: '/panel/lead/campaigns/all',
						element: <LeadPanelCampaignListPage />
					},
					{
						path: '/panel/lead/universities/all',
						element: <LeadPanelUniversityListPage />
					},
					{
						path: '/panel/lead/cities/list',
						element: <LeadPanelCityListPage />
					},
					{
						path: '/panel/lead/countries/list',
						element: <LeadPanelCountryListPage />
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
						path: '/panel/lead/settings/user',
						element: <LeadPanelUserSettings />
					},
					{
						path: '/panel/lead/settings/lead',
						element: <LeadPanelLeadSettings />
					},
					{
						path: '/panel/core-team',
						element: <CoreTeamDashboardPage />
					},
					{
						path: '/panel/core-team/dashboard',
						element: <CoreTeamDashboardPage />
					},
					{
						path: '/panel/core-team/core-team/my',
						element: <CoreTeamPanelCoreTeamPage />
					},
					{
						path: '/panel/core-team/campaigns/current',
						element: <CoreTeamPanelCurrentCampaignListPage />
					},
					{
						path: '/panel/core-team/campaigns/all',
						element: <CoreTeamPanelCampaignListPage />
					},
					{
						path: '/panel/core-team/universities/list',
						element: <CoreTeamPanelUniversityListPage />
					},
					{
						path: '/panel/core-team/cities/list',
						element: <CoreTeamPanelCityListPage />
					},
					{
						path: '/panel/core-team/countries/list',
						element: <CoreTeamPanelCountryListPage />
					},
					{
						path: '/panel/core-team/questions/list',
						element: <NotFoundPage />
					},
					{
						path: '/panel/core-team/questions/ask',
						element: <NotFoundPage />
					},
					{
						path: '/panel/core-team/settings',
						element: <CoreTeamPanelAccountSettings />
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
				    <UserProvider>
						<RouterProvider router={router} />
					</UserProvider>
				</ProviderWrapper>
			</ContextMenuProvider>
		</QueryClientProvider>
	</React.StrictMode>,
);
