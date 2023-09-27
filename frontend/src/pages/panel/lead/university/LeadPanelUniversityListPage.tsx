import { Alert, Button, Center } from '@mantine/core';
import { IconExclamationMark, IconGridDots, IconList, IconMap } from '@tabler/icons-react';
import { useState } from 'react';
import { useGetUniversitiesWithAuth } from '../../../../hooks/university';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { University } from '../../../../types/UniversityTypes';
import UniversitiesMap from '../../../../components/maps/UniversitiesMap';
import UniversityGrid from '../../../../components/grid/UniversityGrid';
import UniversityTable from '../../../../components/table/UniversityTable';
import { PageContainer } from '../../../../components/PageContainer';


type CurrentDisplayType = 'grid' | 'list' | 'map';

const LeadPanelUniversityListPage = () => {

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

	let universityExists = false;

	let {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
	} = useGetUniversitiesWithAuth(axiosSecure);

	let universities: University[] = [] as University[];
	if (allUniversities && allUniversities?.data){
		universities = allUniversities?.data!
		universityExists = true;
	}

	// let leads: LeadModel[] = buddyTeams.leads;

	// let facilitator: FacilitatorModel[] = [buddyTeams.facilitator];

	let content = <Alert mt="md" variant='outline' color="red" title="Error" icon={<IconExclamationMark/>}>No leads found.</Alert>;

	if (universityExists) {
		if (currentDisplayType == 'grid') {
			content = (
			<Center mt="md">
				<UniversityGrid data={universities} isLoading={isUniversitiesLoading} />
			</Center>
			);
		} else if (currentDisplayType == 'list') {
			content = (
				<Center mt="md">
					<UniversityTable data={universities} isLoading={isUniversitiesLoading} />
				</Center>
			);
		} else if (currentDisplayType == 'map') {
			content = (
				<Center mt="md">
					<UniversitiesMap universities={universities} isLoading={isUniversitiesLoading} />
				</Center>
			);
		}
	}


	return (
		<PageContainer title="Leads">
			{ universityExists &&
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

export default LeadPanelUniversityListPage;
