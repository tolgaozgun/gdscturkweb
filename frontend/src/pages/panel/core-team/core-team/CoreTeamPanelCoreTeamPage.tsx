import { Alert, Button, Divider, Flex } from '@mantine/core';
import { IconGridDots, IconList, IconMap } from '@tabler/icons-react';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { useState } from 'react';
import { CoreTeam } from '../../../../types/TeamTypes';
import { CoreTeamMemberModel, LeadModel } from '../../../../types';
import { PageContainer } from '../../../../components/PageContainer';
import LeadGrid from '../../../../components/grid/LeadGrid';
import CoreTeamMemberGrid from '../../../../components/grid/CoreTeamMemberGrid';
import CoreTeamMemberTable from '../../../../components/table/CoreTeamMemberTable';
import LeadTable from '../../../../components/table/LeadTable';
import LeadMap from '../../../../components/maps/LeadMap';
import CoreTeamMemberMap from '../../../../components/maps/CoreTeamMemberMap';
import useGetCoreTeamByCurrentMember from '../../../../hooks/core-team/useGetCoreTeamByCurrentMember';


type DisplayType = 'grid' | 'list' | 'map';


const CoreTeamPanelCoreTeamPage = () => {
	const axiosSecure = useAxiosSecure();

	const [currentDisplayType, setDisplayType] = useState<DisplayType>('grid');

	const handleSelectGrid = () => {
		setDisplayType('grid');
	}

	const handleSelectList = () => {
		setDisplayType('list');
	}
	
	const handleSelectMap = () => [
		setDisplayType('map')
	]

	let {
		data: coreTeamData,
		isLoading: coreTeamLoading,
	} = useGetCoreTeamByCurrentMember(axiosSecure);

	let coreTeam: CoreTeam | null = null;
	if (coreTeamData && coreTeamData?.data){
		coreTeam = coreTeamData?.data!
	}
	console.log(coreTeamData)
    let content = <Alert variant='outline' color='red'>Core Team not found.</Alert>

	if (coreTeam) {
		let members: CoreTeamMemberModel[] = coreTeam.coreTeamMembers;

		let lead: LeadModel[] = [coreTeam.lead];

		
		switch (currentDisplayType) {
			default:
			case 'grid':
				content = (
				<Flex direction="column" p="xs" gap="md">
					<LeadGrid data={lead} isLoading={coreTeamLoading} />
					<Divider />
					<CoreTeamMemberGrid data={members} isLoading={coreTeamLoading} />
				</Flex>
				);
				break;
			case 'list':
				content = (
					<Flex direction="column" gap="md">
						<LeadTable data={lead} isLoading={coreTeamLoading} />
						<Divider />
						<CoreTeamMemberTable data={members} isLoading={coreTeamLoading} />
					</Flex>
				);
				break;
			case 'map':
				content = (
					<Flex direction="column" gap="md">
						<LeadMap data={lead} isLoading={coreTeamLoading} />
						<Divider />
						<CoreTeamMemberMap data={members} isLoading={coreTeamLoading} />
					</Flex>
				)
			break;

		}
	}


	return (
		<PageContainer title="Core Team">
			<Button.Group defaultValue="grid">
				<Button disabled={currentDisplayType == 'grid'} leftIcon={<IconGridDots size="1rem" />} value="grid" onClick={handleSelectGrid} variant="default">
					Grid
				</Button>
				<Button disabled={currentDisplayType == 'list'} leftIcon={<IconList size="1rem" />} value="list" onClick={handleSelectList} variant="default">
					List
				</Button>
				<Button disabled={currentDisplayType == 'map'} leftIcon={<IconMap size="1rem" />} value="list" onClick={handleSelectMap} variant="default">
					Map
				</Button>
			</Button.Group>
			<Flex mt="md">
				{content}
			</Flex>
		</PageContainer>
	);
};

export default CoreTeamPanelCoreTeamPage;
