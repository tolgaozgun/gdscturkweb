
import { Alert, Divider, Flex, Title } from '@mantine/core';
import { PageContainer } from '../../../components/PageContainer';
import ChangeUserProfileForm from '../../../components/forms/ChangeUserProfileForm';
import { useUser } from '../../../contexts/UserContext';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import useGetAllTopics from '../../../hooks/topic/useGetAllTopics';
import { Topic } from '../../../types/TopicTypes';
import LoadingLottie from '../../../components/common/other/LoadingLottie';
import ChangeLeadProfileForm from '../../../components/forms/ChangeLeadProfileForm';
import useGetUserWithRole from '../../../hooks/auth/useGetUserWithRole';
import { LeadModel } from '../../../types';
import { useNavigate } from 'react-router';

const LeadPanelAccountSettings = () => {

    const axiosSecure = useAxiosSecure();
    const navigate = useNavigate();
    const {user, isUserLoading, isUserError} = useUser();
	const {
		data: allTopics,
		isLoading: isTopicsLoading,
		isError: isTopicsError,
	} = useGetAllTopics(axiosSecure);

    const userWithRole = useGetUserWithRole(axiosSecure);


	const topicList: Array<Topic> = allTopics? allTopics?.data! : [];

    let content = <Alert variant='outline' color='red'>User could not be loaded.</Alert>

    if (user) {
        let lead = userWithRole.data?.data?.extra as LeadModel;
        content = (
            <Flex direction="column">
                <Title p={10}>User Info</Title>
                <ChangeUserProfileForm navigate={navigate} padding={10} topicList={topicList} user={user} />
                <Divider />
                <Title p={10}>Lead Info</Title>
                <ChangeLeadProfileForm navigate={navigate} padding={10} lead={lead} />
            </Flex>
        )
    } 

    if (isUserLoading || isTopicsLoading) {
        return <LoadingLottie />
    }
    
    
	return (
		<PageContainer title="Update Profile">
            {content}
		</PageContainer>
	);
};

export default LeadPanelAccountSettings;
