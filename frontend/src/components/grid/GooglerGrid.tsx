import { Box, SimpleGrid } from '@mantine/core';
import LoadingPage from '../../pages/LoadingPage';
import GooglerCard from '../cards/GooglerCard';
import { GooglerModel } from '../../types';

interface GooglerGridProps {
  data: GooglerModel[];
  isLoading: boolean;
}

const GooglerGrid = ({data, isLoading}: GooglerGridProps) => {


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
            {data.map((googler) => 
                <Box key={googler.googlerId}>
                    <GooglerCard key={googler.googlerId} googler={googler} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default GooglerGrid;