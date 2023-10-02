import { Box, Text, Title, Button, Menu, Image } from '@mantine/core';
import { MRT_ColumnDef, MRT_Row, MRT_TableInstance } from 'mantine-react-table';
import { useMemo } from 'react';
import { IconSend, IconUserCircle } from '@tabler/icons-react';
import { BuddyTeam } from '../../../types/BuddyTeamTypes';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import useGetAllBuddyTeams from '../../../hooks/buddy-team/useGetAllBuddyTeams';
import { PageContainer } from '../../../components/PageContainer';
import LoadingPage from '../../LoadingPage';
import BaseTable from '../../../components/table/BaseTable';


type PageType = BuddyTeam

const PanelBuddyTeamListPage = () => {
	const axiosSecure = useAxiosSecure();

	const {
		data: allBuddyTeams,
		isLoading: isBuddyTeamsLoading,
	} = useGetAllBuddyTeams(axiosSecure);

	const columns = useMemo<MRT_ColumnDef<PageType>[]>(
		() => [
		  {
			id: 'team', //id used to define `group` column
			header: 'Team',
			columns: [
			  {
				accessorFn: (row) => `${row.facilitator.user.name} ${row.facilitator.user.surname}`, //accessorFn used to join multiple data into a single cell
				id: 'facilitator', //id is still required when using accessorFn instead of accessorKey
				header: 'Facilitator',
				size: 250,
				filterVariant: 'autocomplete',
				Cell: ({ renderedCellValue, row }) => (
				  <Box
					sx={{
					  display: 'flex',
					  alignItems: 'center',
					  gap: '16px',
					}}
				  >
					<img
					  alt="avatar"
					  height={30}
					  src={row.original.facilitator.user.profileImage}
					  style={{ borderRadius: '50%' }}
					/>
					<span>{renderedCellValue}</span>
				  </Box>
				),
			  },
			  {
				accessorFn: (row) => `${row.leads.length}`, //accessorFn used to join multiple data into a single cell
				enableClickToCopy: true,
				header: 'No of Leads',
				size: 300,
			  },
			],
		  },
		],
		[],
	  );

	

	const renderDetailPanel = (props: {
		row: MRT_Row<PageType>;
		table: MRT_TableInstance<PageType>
	}): React.ReactNode => {
		return (
			<Box
			  sx={{
				display: 'flex',
				justifyContent: 'flex-start',
				alignItems: 'center',
				gap: '16px',
				padding: '16px',
			  }}
			>
			  <img
				alt="avatar"
				height={200}
				src={props.row.original.facilitator.user.profileImage}
				style={{ borderRadius: '50%' }}
			  />
			  <Box sx={{ textAlign: 'center' }}>
				<Title>Biography:</Title>
				<Text>&quot;{props.row.original.facilitator.user.biography}&quot;</Text>
			  </Box>
			</Box>
		)
	};

	const renderTopToolbarCustomActions = ( props: {
		table: MRT_TableInstance<PageType>
	}) => {
		const table = props.table;

		const handleDeactivate = () => {
		  table.getSelectedRowModel().flatRows.map((row) => {
			alert('deactivating ' + row.getValue('name'));
		  });
		};
  
		const handleActivate = () => {
		  table.getSelectedRowModel().flatRows.map((row) => {
			alert('activating ' + row.getValue('name'));
		  });
		};
  
		const handleContact = () => {
		  table.getSelectedRowModel().flatRows.map((row) => {
			alert('contact ' + row.getValue('name'));
		  });
		};
		return (
			<div style={{ display: 'flex', gap: '8px' }}>
			  <Button
				color="red"
				disabled={!table.getIsSomeRowsSelected()}
				onClick={handleDeactivate}
				variant="filled"
			  >
				Deactivate
			  </Button>
			  <Button
				color="green"
				disabled={!table.getIsSomeRowsSelected()}
				onClick={handleActivate}
				variant="filled"
			  >
				Activate
			  </Button>
			  <Button
				color="blue"
				disabled={!table.getIsSomeRowsSelected()}
				onClick={handleContact}
				variant="filled"
			  >
				Contact
			  </Button>
			</div>
		  );
		}
	
	const rowActionMenuItems = () => {
		return (
			<>
				<Menu.Item icon={<IconUserCircle />}>View Profile</Menu.Item>
				<Menu.Item icon={<IconSend />}>Send Email</Menu.Item>
			</>
		)
	}

	if (isBuddyTeamsLoading || !allBuddyTeams) {
		return <LoadingPage />
	}

	return (

		<PageContainer title="Buddy Teams List">
			<BaseTable 
				data={allBuddyTeams?.data!} 
				columns={columns} 
				renderDetailPanel={renderDetailPanel}
				renderTopToolbarCustomActions={renderTopToolbarCustomActions}
				rowActionMenuItems={rowActionMenuItems}
				  />
		</PageContainer>
	);
};

export default PanelBuddyTeamListPage;
