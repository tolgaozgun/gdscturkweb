import { Box, SimpleGrid } from '@mantine/core';
import LoadingPage from '../../pages/LoadingPage';
import { LeadModel } from '../../types';
import UserCard from '../cards/UserCard';

interface LeadTableProps {
  data: LeadModel[];
  isLoading: boolean;
}

const LeadGrid = ({data, isLoading}: LeadTableProps) => {
  
	if (isLoading || !data) {
		return <LoadingPage />;
	}
  console.log(data)

    return (
        <SimpleGrid cols={4} pt="xl" 
        spacing="lg"
        breakpoints={[
          { maxWidth: 'md', cols: 3, spacing: 'md' },
          { maxWidth: 'sm', cols: 2, spacing: 'sm' },
          { maxWidth: 'xs', cols: 1, spacing: 'sm' },
        ]}>
            {data.map((lead) => 
                <Box key={lead.leadId}>
                  <UserCard key={lead.leadId} lead={lead}/>
                    {/* <LeadCard key={lead.leadId} lead={lead} padding='xl' /> */}
                </Box>
            )}
        </SimpleGrid>
  );
};

export default LeadGrid;