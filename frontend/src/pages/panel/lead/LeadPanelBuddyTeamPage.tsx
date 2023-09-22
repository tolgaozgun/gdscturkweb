import { Alert, Button, Center } from '@mantine/core';
import { IconExclamationMark, IconGridDots, IconList } from '@tabler/icons-react';
import LeadTable from '../../../components/table/LeadTable';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import FacilitatorTable from '../../../components/table/FacilitatorTable';
import { useState } from 'react';
import LeadGrid from '../../../components/grid/LeadGrid';
import FacilitatorGrid from '../../../components/grid/FacilitatorGrid';
import { FacilitatorModel, LeadModel } from '../../../types';
import { PageContainer } from '../../../components/PageContainer';
import useGetBuddyTeamByCurrentLead from '../../../hooks/buddy-team/useGetBuddyTeamByCurrentUser';
import { BuddyTeam } from '../../../types/BuddyTeamTypes';



const LeadPanelBuddyTeamPage = () => {

	const axiosSecure = useAxiosSecure();

	const [useGrid, setUseGrid] = useState<boolean>(true);
	

	const handleSelectGrid = () => {
		setUseGrid(true);
	}

	const handleSelectList = () => {
		setUseGrid(false);
	}

	let buddyTeamExists = false;

	let {
		data: buddyTeamData,
		isLoading: buddyTeamLoading,
	} = useGetBuddyTeamByCurrentLead(axiosSecure);

	let buddyTeam: BuddyTeam = {} as BuddyTeam;
	if (buddyTeamData && buddyTeamData?.data){
		buddyTeam = buddyTeamData?.data!
		buddyTeamExists = true;
	}

	let leads: LeadModel[] = buddyTeam.leads;

	let facilitator: FacilitatorModel[] = [buddyTeam.facilitator];

	let content = <Alert variant='outline' color="red" title="Error" icon={<IconExclamationMark/>}>You are not in a buddy team.</Alert>;

	if (buddyTeamExists) {
		if (useGrid) {
			<Center mt="md">
				<FacilitatorGrid data={facilitator} isLoading={buddyTeamLoading} />
				<LeadGrid data={leads} isLoading={buddyTeamLoading} />
			</Center>;
		} else {
			content = (
				<Center mt="md">
					<FacilitatorTable data={facilitator} isLoading={buddyTeamLoading} />
					<LeadTable data={leads} isLoading={buddyTeamLoading} />
				</Center>
			);
		}
	}


	return (
		<PageContainer title="Buddy Team">
			{ buddyTeamExists &&
			<Button.Group defaultValue="grid">
				<Button disabled={useGrid} leftIcon={<IconGridDots size="1rem" />} value="grid" onClick={handleSelectGrid} variant="default">
					Grid
				</Button>
				<Button disabled={!useGrid} leftIcon={<IconList size="1rem" />} value="list" onClick={handleSelectList} variant="default">
					List
				</Button>
			</Button.Group>
			}
			{content}
		</PageContainer>
	);
};

export default LeadPanelBuddyTeamPage;
