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
					}
          // {
            // 404 page
          //   path: '*',
          //   element: <LoadingPage/>
          // }
				],
			},
		],
	},
]);
const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
		<QueryClientProvider client={queryClient}>
			<ProviderWrapper>
				<RouterProvider router={router} />
			</ProviderWrapper>
		</QueryClientProvider>
	</React.StrictMode>,
);
