import { Box, SimpleGrid } from '@mantine/core';
import { AxiosInstance } from 'axios';
import LoadingPage from '../../pages/LoadingPage';
import GooglerCard from '../cards/GooglerCard';
import useGetGooglers from '../../hooks/user/useGetGooglers';

interface GooglerGridProps {
  axiosSecure: AxiosInstance
}

const GooglerGrid = ({axiosSecure}: GooglerGridProps) => {

	const {
		data: allGooglers,
		isLoading: isGooglersLoading,
		// isError: isLeadsError,
	} = useGetGooglers(axiosSecure);

	if (isGooglersLoading || !allGooglers) {
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
            {allGooglers?.data!.map((googler) => 
                <Box key={googler.googlerId}>
                    <GooglerCard key={googler.googlerId} googler={googler} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default GooglerGrid;