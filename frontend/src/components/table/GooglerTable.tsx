import { useMemo } from 'react';
import {
  MantineReactTable,
  useMantineReactTable,
  type MRT_ColumnDef,
} from 'mantine-react-table';
import { Box, Button, Menu, Text, Title } from '@mantine/core';
import { IconUserCircle, IconSend } from '@tabler/icons-react';
import { GooglerModel, UserType } from '../../types';
import { AxiosInstance } from 'axios';
import LoadingPage from '../../pages/LoadingPage';
import useGetGooglers from '../../hooks/user/useGetGooglers';

interface GooglerTableProps {
  axiosSecure: AxiosInstance
}

let data: GooglerModel[] = [
  {
    googlerId: 1,
    city: {
      cityId: 1,
      name: "Example City",
      country: {
        countryId: 1,
        name: "Example Country",
        flagImage: "example-flag.png",
      },
      latitude: 123.456,
      longitude: 789.012,
    },
    country: {
      countryId: 1,
      name: "Example Country",
      flagImage: "example-flag.png",
    },
    user: {
      userId: 1,
      name: "John",
      surname: "Doe",
      email: "john.doe@example.com",
      username: "johndoe",
      userType: UserType.Googler,
      profileImage: "john-doe.png",
      phoneNumber: "1234567890",
      biography: "Lorem ipsum dolor sit amet...",
      interests: []
    },
  },
];


const GooglerTable = ({axiosSecure}: GooglerTableProps) => {

	const {
		data: allGooglers,
		isLoading: isGooglersLoading,
		// isError: isCoreTeamMembersError,
	} = useGetGooglers(axiosSecure);

  if (allGooglers) {
	  data = allGooglers.data!;
  } else {
    data = [];
  }

  const columns = useMemo<MRT_ColumnDef<GooglerModel>[]>(
    () => [
      {
        id: 'user', //id used to define `group` column
        header: 'User',
        columns: [
          {
            accessorFn: (row) => `${row.user.name} ${row.user.surname}`, //accessorFn used to join multiple data into a single cell
            id: 'name', //id is still required when using accessorFn instead of accessorKey
            header: 'Name',
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
                  src={row.original.user.profileImage}
                  style={{ borderRadius: '50%' }}
                />
                <span>{renderedCellValue}</span>
              </Box>
            ),
          },
          {
            accessorKey: 'user.email', //accessorKey used to define `data` column. `id` gets set to accessorKey automatically
            enableClickToCopy: true,
            header: 'Email',
            size: 300,
          },
        ],
      },
      {
        id: 'googler',
        header: 'Googler',
        columns: [
          {
            accessorKey: 'city.name',
            header: 'City',
            size: 200,
            filterVariant: 'autocomplete',
          },
          {
            accessorKey: 'country.name',
            header: 'Country',
            size: 200,
            filterVariant: 'autocomplete',
          },
        ],
      },
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
          src={row.original.user.profileImage}
          style={{ borderRadius: '50%' }}
        />
        <Box sx={{ textAlign: 'center' }}>
          <Title>Biography:</Title>
          <Text>&quot;{row.original.user.biography}&quot;</Text>
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

	if (isGooglersLoading || !allGooglers) {
		return <LoadingPage />;
	}

  return <MantineReactTable table={table} />;
};

export default GooglerTable;