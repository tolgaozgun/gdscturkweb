import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import { useParams } from 'react-router';
import useGetCampaignById from '../hooks/campaign/useGetCampaignById';
import LoadingPage from './LoadingPage';
import { TableOfContents } from '../components/menus/TableOfContents';
import { useRef } from 'react';
import { CampaignPage } from '../types/CampaignTypes';
import HeroComponent from '../components/HeroComponent';
import HeroBg from '../assets/heroBg.jpg';
import { Container, Grid, Title, Text, Image, Button } from '@mantine/core';
import NotFoundPage from './NotFoundPage';

const CampaignDisplayPage = () => {
	const { campaignId } = useParams();
	const ref = useRef<HTMLDivElement[]>([]);
	
	const axiosSecure = useAxiosSecure();
	// campaignId to integer

	if (!campaignId) {
		return <NotFoundPage />
	}

	const {
		data: currentCampaign,
		isLoading: isCampaignLoading,
		isError: isCampaignError,
	} = useGetCampaignById(axiosSecure, parseInt(campaignId));

	let campaignPages: Array<CampaignPage> = [];
	if (currentCampaign && currentCampaign.data && currentCampaign!.data!.campaignPages){
		campaignPages = currentCampaign!.data?.campaignPages;
	} else {
		campaignPages = [];
	}

	

	let links = campaignPages.map((campaignPage) => ({
		label: campaignPage.title,
		content: campaignPage,
	}))

	let title: string = currentCampaign?.data!.title || "";
	let description: string = currentCampaign?.data!.description || "";
	let buttons: Array<string> = ["Join Campaign", "Create Campaign"];


	if (isCampaignLoading && !currentCampaign) {
		return <LoadingPage />
	}
	
	if (isCampaignError) {
		return <NotFoundPage />
	}

	const onClickTableOfContents = (index: number) => {
		if(ref?.current[index])
			ref?.current[index].scrollIntoView({ behavior: 'smooth', block: 'start' });
	}

	return (
			<>
				<HeroComponent title={title} description={description} bg={HeroBg} buttons={buttons}/>
				
					<Grid mt={100}>

					<Grid.Col xs={3} sm={2} md={2} lg={2} pl={30} >
						<TableOfContents links={links} onClick={onClickTableOfContents} />
					</Grid.Col>
					<Grid.Col xs={9} sm={10} md={10} lg={10}>
						{campaignPages.map((campaignPage, index) => {
							let title = campaignPage.title || "";
							let description = campaignPage.description || "";

							let leftImg = <></>;
							let rightImg = <></>;

							if (index % 2 == 0){
								leftImg = (
									<Grid.Col xs={6} sm={4} md={4} lg={4}>
										<Image src={"https://mantinetemplate.netlify.app/static/media/lime-surfing.8ef8682684832594f090.png"} alt={'sample1'} style={{ width: '100%' }} />
									</Grid.Col>
								)
							} else {
								rightImg = (
									<Grid.Col xs={6} sm={4} md={4} lg={4}>
										<Image src={"https://mantinetemplate.netlify.app/static/media/lime-bicycle-riding.b6b8368f8cf9b17c988c.png"} alt={'sample1'} style={{ width: '100%' }} />
									</Grid.Col>
								)
							}
							

							return (
								<Container ref={ref.current[index]} key={index} mb={100} size="xl">
									<Grid justify="space-around">
										{leftImg}
										<Grid.Col xs={6} sm={8} md={8} lg={8}>
											<div style={{ marginBottom: 20 }}>
												<Text color="black">
													<Title order={1}>{title}</Title>
													{description}
												</Text>
											</div>
											<Button color="yellow">Check it out</Button>
										</Grid.Col>
										{rightImg}
									</Grid>
								</Container>
							)
						})}
					</Grid.Col>
				</Grid>
			</>
	);
};

export default CampaignDisplayPage;
