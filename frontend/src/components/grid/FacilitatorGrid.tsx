import { Box, SimpleGrid } from '@mantine/core';
import LoadingPage from '../../pages/LoadingPage';
import FacilitatorCard from '../cards/FacilitatorCard';
import { FacilitatorModel } from '../../types';

interface FacilitatorGridProps {
  data: FacilitatorModel[];
  isLoading: boolean;
}

const FacilitatorGrid = ({data, isLoading}: FacilitatorGridProps) => {

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
            {data.map((facilitator) => 
                <Box key={facilitator.facilitatorId}>
                    <FacilitatorCard key={facilitator.facilitatorId} facilitator={facilitator} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default FacilitatorGrid;