import { Center, Tabs } from '@mantine/core';
import { IconFriends } from '@tabler/icons-react';
import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import LeadMap from '../components/maps/LeadMap';
import CoreTeamMemberMap from '../components/maps/CoreTeamMemberMap';
import FacilitatorMap from '../components/maps/FacilitatorMap';
import GooglerMap from '../components/maps/GooglerMap';
import LeadGrid from '../components/grid/LeadGrid';
import CoreTeamMemberGrid from '../components/grid/CoreTeamMemberGrid';
import FacilitatorGrid from '../components/grid/FacilitatorGrid';
import GooglerGrid from '../components/grid/GooglerGrid';
import useGetLeads from '../hooks/user/useGetLeads';
import { CoreTeamMemberModel, FacilitatorModel, GooglerModel, LeadModel } from '../types';
import useGetCoreTeamMembers from '../hooks/user/useGetCoreTeamMembers';
import useGetFacilitators from '../hooks/user/useGetFacilitators';
import useGetGooglers from '../hooks/user/useGetGooglers';



const MapPage = () => {
	const axiosSecure = useAxiosSecure();

	let {
		data: leadsData,
		isLoading: leadsLoading,
	} = useGetLeads(axiosSecure);

	let leads: LeadModel[] = [];
	if (leadsData && leadsData?.data){
		leads = leadsData?.data!
	}
	let {
		data: coreTeamMemberData,
		isLoading: coreTeamLoading,
	} = useGetCoreTeamMembers(axiosSecure);
	
	let coreTeamMembers : CoreTeamMemberModel[] = [];
	if (coreTeamMemberData && coreTeamMemberData?.data) {
		coreTeamMembers = coreTeamMemberData?.data!
	}

	let {
		data: facilitatorData,
		isLoading: facilitatorLoading,
	} = useGetFacilitators(axiosSecure);

	let facilitators: FacilitatorModel[] = [];
	if (facilitatorData && facilitatorData?.data) {
		facilitators = facilitatorData?.data!
	}

	let {
		data: googlerData,
		isLoading: googlerLoading,
	} = useGetGooglers(axiosSecure);

	let googlers: GooglerModel[] = [];
	if (googlerData && googlerData?.data) {
		googlers = googlerData?.data!
	}

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
					<LeadMap data={leads} isLoading={leadsLoading} />
					<LeadGrid data={leads} isLoading={leadsLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="core-team" pt="xs">
					<CoreTeamMemberMap data={coreTeamMembers} isLoading={coreTeamLoading} />
					<CoreTeamMemberGrid data={coreTeamMembers} isLoading={coreTeamLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="xs">
					<FacilitatorMap data={facilitators} isLoading={facilitatorLoading} />
					<FacilitatorGrid data={facilitators} isLoading={facilitatorLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="googler" pt="xs">
					<GooglerMap data={googlers} isLoading={googlerLoading} />
					<GooglerGrid data={googlers} isLoading={googlerLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="admin" pt="xs">
					{/* <AdminMap axiosSecure={axiosSecure} /> */}

				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default MapPage;
