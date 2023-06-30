import { Box, SimpleGrid } from '@mantine/core';
import { AxiosInstance } from 'axios';
import LoadingPage from '../../pages/LoadingPage';
import useGetFacilitators from '../../hooks/user/useGetFacilitators';
import FacilitatorCard from '../cards/FacilitatorCard';

interface FacilitatorGridProps {
  axiosSecure: AxiosInstance
}

const FacilitatorGrid = ({axiosSecure}: FacilitatorGridProps) => {

	const {
		data: allFacilitators,
		isLoading: isFacilitatorsLoading,
		// isError: isLeadsError,
	} = useGetFacilitators(axiosSecure);

	if (isFacilitatorsLoading || !allFacilitators) {
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
            {allFacilitators?.data!.map((facilitator) => 
                <Box key={facilitator.facilitatorId}>
                    <FacilitatorCard key={facilitator.facilitatorId} facilitator={facilitator} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default FacilitatorGrid;