import { Button, Center, Tabs } from '@mantine/core';
import { IconFriends, IconGridDots, IconList, IconMap } from '@tabler/icons-react';
import LeadTable from '../components/table/LeadTable';
import useAxiosSecure from '../hooks/auth/useAxiosSecure';
import FacilitatorTable from '../components/table/FacilitatorTable';
import CoreTeamMemberTable from '../components/table/CoreTeamMemberTable';
import GooglerTable from '../components/table/GooglerTable';
import { useState } from 'react';
import useGetCoreTeamMembers from '../hooks/user/useGetCoreTeamMembers';
import { CoreTeamMemberModel, FacilitatorModel, GooglerModel, LeadModel } from '../types';
import useGetLeads from '../hooks/user/useGetLeads';
import useGetFacilitators from '../hooks/user/useGetFacilitators';
import useGetGooglers from '../hooks/user/useGetGooglers';
import LeadGrid from '../components/grid/LeadGrid';
import CoreTeamMemberGrid from '../components/grid/CoreTeamMemberGrid';
import FacilitatorGrid from '../components/grid/FacilitatorGrid';
import GooglerGrid from '../components/grid/GooglerGrid';


type DisplayType = 'grid' | 'list' | 'map';

const UserListPage = () => {

	// const [selectedIndex, setSelectedIndex] = useState<number>(0);
	
	// const [allCoreTeamMembers, setCoreTeamMembers] = useState<CoreTeamMemberModel[]>([]);
	// const [isCoreTeamMembersLoading, setCoreTeamMembersLoading] = useState<boolean>(true);

	// const [allLeads, setLeads] = useState<LeadModel[]>([]);
	// const [isLeadsLoading, setLeadsLoading] = useState<boolean>(true);

	// const [allFacilitators, setFacilitators] = useState<FacilitatorModel[]>([]);
	// const [isFacilitatorsLoading, setFacilitatorsLoading] = useState<boolean>(true);

	// const [allGooglers, setGooglers] = useState<GooglerModel[]>([]);
	// const [isGooglersLoading, setGooglersLoading] = useState<boolean>(true);

	const axiosSecure = useAxiosSecure();

	const [currentDisplayType, setDisplayType] = useState<DisplayType>('grid');
	

	const handleSelectGrid = () => {
		setDisplayType('grid');
	}

	const handleSelectList = () => {
		setDisplayType('list');
	}

	const handleSelectMap = () => {
		setDisplayType('map');
	}


	// useEffect(() => {
	// 	let {
	// 		data: leadsData,
	// 		isLoading: leadsLoading,
	// 	} = useGetLeads(axiosSecure);

	// 	let leads: LeadModel[] = [];
	// 	if (leadsData && leadsData?.data){
	// 		leads = leadsData?.data!
	// 	}
	// 	setLeads(leads);
	// 	setLeadsLoading(leadsLoading);
	// }, []);

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
	

	// useEffect(() => {
		
	// }, [selectedIndex])

	// useEffect(() => {
	// }, [load[2]])

	// useEffect(() => {
	// }, [load[3]])

	// useEffect(() => {
	// 	let {
	// 		data: data,
	// 		isLoading: isLoading,
	// 	} = useGetG(axiosSecure);
	// 	setCoreTeamMembers(data?.data!);
	// 	setCoreTeamMembersLoading(isLoading);
	// }

	// const handleSelect = (value: TabsValue) => {

	// 	if (value == null || value.toString().trim().length < 1){
	// 		return;
	// 	}

	// 	const index = parseInt(value?.toString());
		
	// 	if (index > 4 || index < 0) {
	// 		return;
	// 	}

	// 	onChange(index);
	// }

	let panels = <></>;

	if (currentDisplayType) {
		panels = <>
			<Tabs.Panel value="lead" pt="xs">
				<LeadGrid data={leads} isLoading={leadsLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="core-team" pt="xs">
				<CoreTeamMemberGrid data={coreTeamMembers} isLoading={coreTeamLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="facilitator" pt="xs">
				<FacilitatorGrid data={facilitators} isLoading={facilitatorLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="googler" pt="xs">
				<GooglerGrid data={googlers} isLoading={googlerLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="admin" pt="xs">

			</Tabs.Panel>
		</>;
	} else {
		panels = (
			<>
				<Tabs.Panel value="lead" pt="xs">
					<LeadTable data={leads} isLoading={leadsLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="core-team" pt="xs">
					<CoreTeamMemberTable data={coreTeamMembers} isLoading={coreTeamLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="xs">
					<FacilitatorTable data={facilitators} isLoading={facilitatorLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="googler" pt="xs">
					<GooglerTable data={googlers} isLoading={googlerLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="admin" pt="xs">

				</Tabs.Panel>
			</>
		);
	}

	return (
		<>
			<Button.Group defaultValue="grid">
				<Button disabled={currentDisplayType == 'grid'} leftIcon={<IconGridDots size="1rem" />} value="grid" onClick={handleSelectGrid} variant="default">
					Grid
				</Button>
				<Button disabled={currentDisplayType == 'list'} leftIcon={<IconList size="1rem" />} value="list" onClick={handleSelectList} variant="default">
					List
				</Button>
				<Button disabled={currentDisplayType == 'map'} leftIcon={<IconMap size="1rem" />} value="list" onClick={handleSelectMap} variant="default">
					Map
				</Button>
			</Button.Group>
			<Center mt="md">
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
					{panels}
				</Tabs>
			</Center>
		</>
	);
};

export default UserListPage;
