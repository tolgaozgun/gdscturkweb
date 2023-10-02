
import { Alert, Flex } from '@mantine/core';
import { PageContainer } from '../../../components/PageContainer';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import ChangeLeadProfileForm from '../../../components/forms/ChangeLeadProfileForm';
import useGetUserWithRole from '../../../hooks/auth/useGetUserWithRole';
import { LeadModel } from '../../../types';
import { useNavigate } from 'react-router';

const LeadPanelLeadSettings = () => {

    const axiosSecure = useAxiosSecure();
    const navigate = useNavigate();

    const {
        data: userWithRole,
        isLoading: isUserWithRoleLoading,
        isError: isUserWithRoleError,
    } = useGetUserWithRole(axiosSecure);

    let content = <Alert variant='outline' color='red'>Lead info could not be loaded.</Alert>

    if (userWithRole) {
        let lead = userWithRole.data?.extra as LeadModel;
        console.log(lead)
        content = (
            <Flex direction="column">
                <ChangeLeadProfileForm navigate={navigate} padding={10} lead={lead} />
            </Flex>
        )
    } 

	return (
		<PageContainer title="Lead Settings">
            {content}
		</PageContainer>
	);
};

export default LeadPanelLeadSettings;
