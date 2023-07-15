import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import { useParams } from 'react-router';
import useGetCampaignById from '../hooks/campaign/useGetCampaignById';
import LoadingPage from './LoadingPage';
import { Text, Title } from '@mantine/core';
import { TableOfContents } from '../components/menus/TableOfContents';
import { useState } from 'react';
import { CampaignPage } from '../types/CampaignTypes';

const CampaignDisplayPage = () => {
	const { campaignId } = useParams();
	
	const [currentPage, setCurrentPage] = useState<CampaignPage>();

	const axiosSecure = useAxiosSecure();
	// campaignId to integer

	if (!campaignId) {
		return <div>Error</div>
	}

	const {
		data: currentCampaign,
		isLoading: isCampaignLoading,
		// isError: isLeadsError,
	} = useGetCampaignById(axiosSecure, parseInt(campaignId));

	const onClickPage = (campaignPage: CampaignPage) => {
		setCurrentPage(campaignPage);	
	}
	
	let campaignPages: Array<CampaignPage>; 
	if (currentCampaign!.data?.campaignPages) {
		campaignPages = currentCampaign!.data?.campaignPages;
		setCurrentPage(campaignPages[0]);
	} else {
		campaignPages = [];
	}

	let links = campaignPages.map((campaignPage) => ({
		label: campaignPage.title,
		content: campaignPage,
		onClick: onClickPage,
	}))


	if (isCampaignLoading && !currentCampaign) {
		return <LoadingPage />
	}

	return (
			<>
				<Title>{currentCampaign?.data!.title}</Title>
				<Text>{currentCampaign?.data!.description}</Text>
				<TableOfContents links={links} active={currentPage ? currentPage.title: ""} />
				{currentPage &&
				<>
					<Title>{currentPage.title}</Title>
					<Text>{currentPage.description}</Text>
				</>
				}
			</>
	);
};

export default CampaignDisplayPage;
