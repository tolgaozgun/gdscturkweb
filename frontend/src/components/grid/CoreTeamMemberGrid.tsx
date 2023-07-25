import { Box, SimpleGrid } from '@mantine/core';
import LoadingPage from '../../pages/LoadingPage';
import CoreTeamMemberCard from '../cards/CoreTeamMemberCard';
import { CoreTeamMemberModel } from '../../types';

interface CoreTeamMemberGridProps {
  data: CoreTeamMemberModel[];
  isLoading: boolean;
}

const CoreTeamMemberGrid = ({data, isLoading}: CoreTeamMemberGridProps) => {

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
            {data.map((coreTeamMember) => 
                <Box key={coreTeamMember.coreTeamMemberId}>
                    <CoreTeamMemberCard key={coreTeamMember.coreTeamMemberId} coreTeamMember={coreTeamMember} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default CoreTeamMemberGrid;