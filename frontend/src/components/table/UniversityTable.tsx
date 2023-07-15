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
import { useGetUniversitiesWithAuth } from '../../hooks/university/useGetUniversities';
import { University } from '../../types/UniversityTypes';

interface UniversityTableProps {
  axiosSecure: AxiosInstance
}

let data: University[] = [

]


const UniversityTable = ({axiosSecure}: UniversityTableProps) => {

	const {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
		// isError: isCoreTeamMembersError,
	} = useGetUniversitiesWithAuth(axiosSecure);

  if (allUniversities) {
	  data = allUniversities.data!;
  } else {
    data = [];
  }

  const columns = useMemo<MRT_ColumnDef<University>[]>(
    () => [
      {
        id: 'university', //id used to define `group` column
        header: 'University',
        columns: [
          {
            accessorFn: (row) => `${row.name}`, //accessorFn used to join multiple data into a single cell
            id: 'name', //id is still required when using accessorFn instead of accessorKey
            header: 'Name',
            size: 250,
            filterVariant: 'autocomplete',
            Cell: ({ renderedCellValue, /*row*/ }) => (
              <Box
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  gap: '16px',
                }}
              >
                {/* <img
                  alt="avatar"
                  height={30}
                  src={row.original.}
                  style={{ borderRadius: '50%' }}
                /> */}
                <span>{renderedCellValue}</span>
              </Box>
            ),
          },
          {
            accessorFn: (row) => `${row.city.name}`, //accessorFn used to join multiple data into a single cell
            enableClickToCopy: true,
            header: 'City',
            size: 300,
          },
          {
            accessorFn: (row) => `${row.country.name}`, //accessorFn used to join multiple data into a single cell
            enableClickToCopy: true,
            header: 'Country',
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
        {/* <img
          alt="avatar"
          height={200}
          src={row.original.facilitator.user.profileImage}
          style={{ borderRadius: '50%' }}
        /> */}
        <Box sx={{ textAlign: 'center' }}>
          <Title>Biography:</Title>
          <Text>&quot;{row.original.name}&quot;</Text>
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

	if (isUniversitiesLoading || !allUniversities) {
		return <LoadingPage />;
	}

  return <MantineReactTable table={table} />;
};

export default UniversityTable;