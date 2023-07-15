import { useMemo } from 'react';
import {
  MantineReactTable,
  useMantineReactTable,
  type MRT_ColumnDef,
} from 'mantine-react-table';
import { Box, Button, Menu, Text, Title } from '@mantine/core';
import { IconUserCircle, IconSend } from '@tabler/icons-react';
import { AxiosInstance } from 'axios';
import LoadingPage from '../../pages/LoadingPage';
import useGetBuddyTeams from '../../hooks/buddy-team/useGetBuddyTeams';
import { BuddyTeam } from '../../types/BuddyTeamTypes';

interface BuddyTeamTableProps {
  axiosSecure: AxiosInstance
}

let data: BuddyTeam[] = [

]


const BuddyTeamTable = ({axiosSecure}: BuddyTeamTableProps) => {

	const {
		data: allBuddyTeams,
		isLoading: isBuddyTeamsLoading,
		// isError: isCoreTeamMembersError,
	} = useGetBuddyTeams(axiosSecure);

  if (allBuddyTeams) {
	  data = allBuddyTeams.data!;
  } else {
    data = [];
  }

  const columns = useMemo<MRT_ColumnDef<BuddyTeam>[]>(
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
      // {
      //   id: 'coreTeamMember',
      //   header: 'Core Team Member',
      //   columns: [
      //     {
      //       accessorKey: 'university.name',
      //       header: 'University',
      //       size: 200,
      //       filterVariant: 'autocomplete',
      //     },
      //     {
      //       accessorKey: 'university.city.name',
      //       header: 'City',
      //       size: 200,
      //       filterVariant: 'autocomplete',
      //     },
      //     {
      //       accessorKey: 'university.country.name',
      //       header: 'Country',
      //       size: 200,
      //       filterVariant: 'autocomplete',
      //     },
      //   ],
      // },
    ],
    [],
  );

  const table = useMantineReactTable({
    columns,
    data,
    enableColumnFilterModes: true,
    enableColumnOrdering: true,
    enableFacetedValues: true,
    enableGrouping: true,
    enablePinning: true,
    enableRowActions: true,
    enableRowSelection: true,
    initialState: { showColumnFilters: true },
    positionToolbarAlertBanner: 'bottom',
    renderDetailPanel: ({ row }) => (
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
          src={row.original.facilitator.user.profileImage}
          style={{ borderRadius: '50%' }}
        />
        <Box sx={{ textAlign: 'center' }}>
          <Title>Biography:</Title>
          <Text>&quot;{row.original.facilitator.user.biography}&quot;</Text>
        </Box>
      </Box>
    ),
    renderRowActionMenuItems: () => (
      <>
        <Menu.Item icon={<IconUserCircle />}>View Profile</Menu.Item>
        <Menu.Item icon={<IconSend />}>Send Email</Menu.Item>
      </>
    ),
    renderTopToolbarCustomActions: ({ table }) => {
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
    },
  });

	if (isBuddyTeamsLoading || !allBuddyTeams) {
		return <LoadingPage />;
	}

  return <MantineReactTable table={table} />;
};

export default BuddyTeamTable;