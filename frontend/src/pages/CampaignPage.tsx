import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import { useParams } from 'react-router';
import useGetCampaignById from '../hooks/campaign/useGetCampaignById';
import { GetCampaignByIdRequest } from '../types/CampaignTypes';
import LoadingPage from './LoadingPage';
import { Text, Title } from '@mantine/core';

const CampaignPage = () => {
	const { campaignId } = useParams();
	const axiosSecure = useAxiosSecure();
	// campaignId to integer

	if (!campaignId) {
		return <div>Error</div>
	}

	const getCampaignByIdRequest: GetCampaignByIdRequest = {
		campaignId: parseInt(campaignId)
	}

	const {
		data: campaign,
		isLoading: isCampaignLoading,
		// isError: isLeadsError,
	} = useGetCampaignById(axiosSecure, getCampaignByIdRequest);

	if (isCampaignLoading && !campaign) {
		return <LoadingPage />
	}

	return (
			<>
				<Title>{campaign?.data!.title}</Title>
				<Text>{campaign?.data!.description}</Text>
			</>
	);
};

export default CampaignPage;
