import { Box, SimpleGrid } from '@mantine/core';
import { AxiosInstance } from 'axios';
import useGetLeads from '../../hooks/user/useGetLeads';
import LoadingPage from '../../pages/LoadingPage';
import LeadCard from '../cards/LeadCard';

interface LeadTableProps {
  axiosSecure: AxiosInstance
}

const LeadGrid = ({axiosSecure}: LeadTableProps) => {

	const {
		data: allLeads,
		isLoading: isLeadsLoading,
		// isError: isLeadsError,
	} = useGetLeads(axiosSecure);

	if (isLeadsLoading || !allLeads) {
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
            {allLeads?.data!.map((lead) => 
                <Box key={lead.leadId}>
                    <LeadCard key={lead.leadId} lead={lead} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default LeadGrid;