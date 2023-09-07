import { Button, Center, Tabs } from '@mantine/core';
import { IconFriends, IconGridDots, IconList } from '@tabler/icons-react';
import LeadTable from '../../../components/table/LeadTable';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import FacilitatorTable from '../../../components/table/FacilitatorTable';
import CoreTeamMemberTable from '../../../components/table/CoreTeamMemberTable';
import GooglerTable from '../../../components/table/GooglerTable';
import { useState } from 'react';
import useGetLeads from '../../../hooks/user/useGetLeads';
import useGetFacilitators from '../../../hooks/user/useGetFacilitators';
import useGetGooglers from '../../../hooks/user/useGetGooglers';
import LeadGrid from '../../../components/grid/LeadGrid';
import CoreTeamMemberGrid from '../../../components/grid/CoreTeamMemberGrid';
import FacilitatorGrid from '../../../components/grid/FacilitatorGrid';
import GooglerGrid from '../../../components/grid/GooglerGrid';
import { CoreTeamMemberModel, FacilitatorModel, GooglerModel, LeadModel } from '../../../types';
import useGetCoreTeamMembers from '../../../hooks/user/useGetCoreTeamMembers';
import { PageContainer } from '../../../components/PageContainer';
import { useNavigate } from 'react-router';



const PanelUserListPage = () => {

	const axiosSecure = useAxiosSecure();
	const navigate = useNavigate();

	const [useGrid, setUseGrid] = useState<boolean>(true);
	

	const handleSelectGrid = () => {
		setUseGrid(true);
	}

	const handleSelectList = () => {
		setUseGrid(false);
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


	const handleLeadCreate = () => {
		navigate("/panel/admin/users/create-lead");
	}
	const handleCoreTeamCreate = () => {
		navigate("/panel/admin/users/create-core-team-member");
	}
	const handleFacilitatorCreate = () => {
		navigate("/panel/admin/users/create-facilitator");
	}

	let panels = <></>;

	if (useGrid) {
		panels = <>
			<Tabs.Panel value="lead" pt="xs">
				<Button onClick={handleLeadCreate}>Create Lead</Button>
				<LeadGrid data={leads} isLoading={leadsLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="core-team" pt="xs">
				<Button onClick={handleCoreTeamCreate}>Create Core Team Member</Button>
				<CoreTeamMemberGrid data={coreTeamMembers} isLoading={coreTeamLoading} />
			</Tabs.Panel>
			<Tabs.Panel value="facilitator" pt="xs">
				<Button onClick={handleFacilitatorCreate}>Create Facilitator</Button>
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
					<Button onClick={handleLeadCreate}>Create Lead</Button>
					<LeadTable data={leads} isLoading={leadsLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="core-team" pt="xs">
					<Button onClick={handleCoreTeamCreate}>Create Core Team Member</Button>
					<CoreTeamMemberTable data={coreTeamMembers} isLoading={coreTeamLoading} />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="xs">
				<Button onClick={handleFacilitatorCreate}>Create Facilitator</Button>
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
		<PageContainer title="User List">
			<Button.Group defaultValue="grid">
				<Button disabled={useGrid} leftIcon={<IconGridDots size="1rem" />} value="grid" onClick={handleSelectGrid} variant="default">
					Grid
				</Button>
				<Button disabled={!useGrid} leftIcon={<IconList size="1rem" />} value="list" onClick={handleSelectList} variant="default">
					List
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
		</PageContainer>
	);
};

export default PanelUserListPage;
