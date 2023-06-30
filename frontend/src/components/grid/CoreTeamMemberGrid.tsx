import { Box, SimpleGrid } from '@mantine/core';
import { AxiosInstance } from 'axios';
import LoadingPage from '../../pages/LoadingPage';
import useGetCoreTeamMembers from '../../hooks/user/useGetCoreTeamMembers';
import CoreTeamMemberCard from '../cards/CoreTeamMemberCard';

interface CoreTeamMemberGridProps {
  axiosSecure: AxiosInstance
}

const CoreTeamMemberGrid = ({axiosSecure}: CoreTeamMemberGridProps) => {

	const {
		data: allCoreTeamMembers,
		isLoading: isCoreTeamMembersLoading,
		// isError: isLeadsError,
	} = useGetCoreTeamMembers(axiosSecure);

	if (isCoreTeamMembersLoading || !allCoreTeamMembers) {
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
            {allCoreTeamMembers?.data!.map((coreTeamMember) => 
                <Box key={coreTeamMember.coreTeamMemberId}>
                    <CoreTeamMemberCard key={coreTeamMember.coreTeamMemberId} coreTeamMember={coreTeamMember} padding='xl' />
                </Box>
            )}
        </SimpleGrid>
  );
};

export default CoreTeamMemberGrid;