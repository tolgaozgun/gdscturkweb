import { Center, Tabs } from '@mantine/core';
import { IconFriends } from '@tabler/icons-react';
import LeadTable from '../../components/table/LeadTable';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import FacilitatorTable from '../../components/table/FacilitatorTable';
import CoreTeamMemberTable from '../../components/table/CoreTeamMemberTable';
import GooglerTable from '../../components/table/GooglerTable';
import { PageContainer } from '../../components/PageContainer';



const PanelUserListPage = () => {
	const axiosSecure = useAxiosSecure();

	return (
		<PageContainer title="User List">
			<Center>
				<Tabs variant="pills" style={{width: '100%'}} keepMounted={false} defaultValue="lead">
					<Tabs.List>
						<Tabs.Tab icon={<IconFriends size="1rem" />} value="lead">
							Lead
						</Tabs.Tab>
						<Tabs.Tab icon={<IconFriends size="1rem" />} value="core-team">
							Core Team Member
						</Tabs.Tab>
						<Tabs.Tab icon={<IconFriends size="1rem" />} value="facilitator">
							Facilitator
						</Tabs.Tab>
						<Tabs.Tab icon={<IconFriends size="1rem" />} value="googler">
							Googler
						</Tabs.Tab>
						<Tabs.Tab icon={<IconFriends size="1rem" />} value="admin">
							Admin
						</Tabs.Tab>
					</Tabs.List>
					<Tabs.Panel value="lead" pt="xs">
						<LeadTable axiosSecure={axiosSecure} />
					</Tabs.Panel>
					<Tabs.Panel value="core-team" pt="xs">
						<CoreTeamMemberTable axiosSecure={axiosSecure} />
					</Tabs.Panel>
					<Tabs.Panel value="facilitator" pt="xs">
						<FacilitatorTable axiosSecure={axiosSecure} />
					</Tabs.Panel>
					<Tabs.Panel value="googler" pt="xs">
						<GooglerTable axiosSecure={axiosSecure} />
					</Tabs.Panel>
					<Tabs.Panel value="admin" pt="xs">

					</Tabs.Panel>
				</Tabs>
			</Center>
		</PageContainer>
	);
};

export default PanelUserListPage;
