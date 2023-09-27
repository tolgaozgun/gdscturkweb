import { Alert, Button, Center } from '@mantine/core';
import { IconExclamationMark, IconGridDots, IconList, IconMap } from '@tabler/icons-react';
import LeadTable from '../../../components/table/LeadTable';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { useState } from 'react';
import LeadGrid from '../../../components/grid/LeadGrid';
import { LeadModel } from '../../../types';
import { PageContainer } from '../../../components/PageContainer';
import useGetLeads from '../../../hooks/user/useGetLeads';
import LeadMap from '../../../components/maps/LeadMap';


type CurrentDisplayType = 'grid' | 'list' | 'map';

const LeadPanelLeadListPage = () => {

	const axiosSecure = useAxiosSecure();

	const [currentDisplayType, setCurrentDisplayType] = useState<CurrentDisplayType>('grid');
	

	const handleSelectGrid = () => {
		setCurrentDisplayType('grid');
	}

	const handleSelectList = () => {
		setCurrentDisplayType('list');
	}

	const handleSelectMap = () => [
		setCurrentDisplayType('map')
	]

	let leadExists = false;

	let {
		data: allLeads,
		isLoading: isLeadsLoading,
	} = useGetLeads(axiosSecure);

	let leads: LeadModel[] = [] as LeadModel[];
	if (allLeads && allLeads?.data){
		leads = allLeads?.data!
		leadExists = true;
	}

	// let leads: LeadModel[] = buddyTeams.leads;

	// let facilitator: FacilitatorModel[] = [buddyTeams.facilitator];

	let content = <Alert mt="md" variant='outline' color="red" title="Error" icon={<IconExclamationMark/>}>No leads found.</Alert>;

	if (leadExists) {
		if (currentDisplayType == 'grid') {
			content = (
			<Center mt="md">
				<LeadGrid data={leads} isLoading={isLeadsLoading} />
			</Center>
			);
		} else if (currentDisplayType == 'list') {
			content = (
				<Center mt="md">
					<LeadTable data={leads} isLoading={isLeadsLoading} />
				</Center>
			);
		} else if (currentDisplayType == 'map') {
			content = (
				<Center mt="md">
					<LeadMap data={leads} isLoading={isLeadsLoading} />
				</Center>
			);
		}
	}


	return (
		<PageContainer title="Leads">
			{ leadExists &&
			<Button.Group defaultValue="grid">
				<Button disabled={currentDisplayType == 'grid'} leftIcon={<IconGridDots size="1rem" />} value="grid" onClick={handleSelectGrid} variant="default">
					Grid
				</Button>
				<Button disabled={currentDisplayType == 'list'} leftIcon={<IconList size="1rem" />} value="list" onClick={handleSelectList} variant="default">
					List
				</Button>
				<Button disabled={currentDisplayType == 'map'} leftIcon={<IconMap size="1rem" />} value="map" onClick={handleSelectMap} variant="default">
					Map
				</Button>
			</Button.Group>
			}
			{content}
		</PageContainer>
	);
};

export default LeadPanelLeadListPage;
