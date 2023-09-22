
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
import { CoreTeamMemberModel, LeadModel } from '../../../types';
import ChangeCoreTeamProfileForm from '../../../components/forms/ChangeCoreTeamProfileForm';

const CoreTeamPanelAccountSettings = () => {

    const axiosSecure = useAxiosSecure();
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
        let coreTeamMember = userWithRole.data?.data?.extra as CoreTeamMemberModel;
        content = (
            <Flex direction="column">
                <Title p={10}>User Info</Title>
                <ChangeUserProfileForm padding={10} topicList={topicList} user={user} />
                <Divider />
                <Title p={10}>Core Team Member Info</Title>
                <ChangeCoreTeamProfileForm padding={10} coreTeamMember={coreTeamMember} />
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

export default CoreTeamPanelAccountSettings;
