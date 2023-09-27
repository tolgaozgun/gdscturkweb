import { Box, SimpleGrid } from '@mantine/core';
import LoadingPage from '../../pages/LoadingPage';
import { LeadModel } from '../../types';
import UserCard from '../cards/UserCard';
import { University } from '../../types/UniversityTypes';
import UniversityCard from '../cards/UniversityCard';

interface LeadTableProps {
  data: University[];
  isLoading: boolean;
}

const UniversityGrid = ({data, isLoading}: LeadTableProps) => {
  
	if (isLoading || !data) {
		return <LoadingPage />;
	}

    return (
        <SimpleGrid cols={4} pt="xl" 
        spacing="lg"
        breakpoints={[
          { maxWidth: 'md', cols: 3, spacing: 'md' },
          { maxWidth: 'sm', cols: 2, spacing: 'sm' },
          { maxWidth: 'xs', cols: 1, spacing: 'sm' },
        ]}>
            {data.map((university) => 
                <Box key={university.universityId}>
                  <UniversityCard key={university.universityId} university={university}/>
                    {/* <LeadCard key={lead.leadId} lead={lead} padding='xl' /> */}
                </Box>
            )}
        </SimpleGrid>
  );
};

export default UniversityGrid;