
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

const LeadPanelUserSettings = () => {

    const axiosSecure = useAxiosSecure();
    const navigate = useNavigate();
    const {user, isUserLoading, isUserError} = useUser();
	const {
		data: allTopics,
		isLoading: isTopicsLoading,
		isError: isTopicsError,
	} = useGetAllTopics(axiosSecure);

	const topicList: Array<Topic> = allTopics? allTopics?.data! : [];

    let content = <Alert variant='outline' color='red'>User could not be loaded.</Alert>

    if (user) {
        content = (
            <Flex direction="column">
                <ChangeUserProfileForm navigate={navigate} padding={10} topicList={topicList} user={user} />
            </Flex>
        )
    } 

    if (isUserLoading || isTopicsLoading) {
        return <LoadingLottie />
    }
    
    
	return (
		<PageContainer title="User Settings">
            {content}
		</PageContainer>
	);
};

export default LeadPanelUserSettings;
