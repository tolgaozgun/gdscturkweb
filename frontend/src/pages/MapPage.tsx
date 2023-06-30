import { Center, Tabs } from '@mantine/core';
import { IconFriends } from '@tabler/icons-react';
import LeadTable from '../components/table/LeadTable';
import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import FacilitatorTable from '../components/table/FacilitatorTable';
import CoreTeamMemberTable from '../components/table/CoreTeamMemberTable';
import GooglerTable from '../components/table/GooglerTable';
import LeadMap from '../components/maps/LeadMap';
import CoreTeamMemberMap from '../components/maps/CoreTeamMemberMap';
import FacilitatorMap from '../components/maps/FacilitatorMap';
import GooglerMap from '../components/maps/GooglerMap';
import LeadGrid from '../components/grid/LeadGrid';
import CoreTeamMemberGrid from '../components/grid/CoreTeamMemberGrid';
import FacilitatorGrid from '../components/grid/FacilitatorGrid';
import GooglerGrid from '../components/grid/GooglerGrid';



const MapPage = () => {
	const axiosSecure = useAxiosSecure();

	return (
		<Center>

			<Tabs variant="pills" keepMounted={false} style={{width: '100%'}} defaultValue="lead">
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
					<LeadMap axiosSecure={axiosSecure} />
					<LeadGrid axiosSecure={axiosSecure} />
				</Tabs.Panel>
				<Tabs.Panel value="core-team" pt="xs">
					<CoreTeamMemberMap axiosSecure={axiosSecure} />
					<CoreTeamMemberGrid axiosSecure={axiosSecure} />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="xs">
					<FacilitatorMap axiosSecure={axiosSecure} />
					<FacilitatorGrid axiosSecure={axiosSecure} />
				</Tabs.Panel>
				<Tabs.Panel value="googler" pt="xs">
					<GooglerMap axiosSecure={axiosSecure} />
					<GooglerGrid axiosSecure={axiosSecure} />
				</Tabs.Panel>
				<Tabs.Panel value="admin" pt="xs">
					{/* <AdminMap axiosSecure={axiosSecure} /> */}

				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default MapPage;
