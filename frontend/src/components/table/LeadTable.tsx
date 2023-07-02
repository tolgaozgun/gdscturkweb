import { useMemo } from 'react';
import {
  MantineReactTable,
  useMantineReactTable,
  type MRT_ColumnDef,
} from 'mantine-react-table';
import { Box, Button, Menu, Text, Title, Image } from '@mantine/core';
import { IconUserCircle, IconSend } from '@tabler/icons-react';
import { UserModel, UserType } from '../../types';
import { University } from '../../types/UniversityTypes';
import { AxiosInstance } from 'axios';
import useGetLeads from '../../hooks/user/useGetLeads';
import LoadingPage from '../../pages/LoadingPage';
import DefaultProfilePicture from "../../assets/default_profile_picture.png";
import { BuddyTeam } from '../../types/BuddyTeamTypes';

interface LeadTableProps {
  axiosSecure: AxiosInstance
}

type LeadModel = {
	leadId: number;
	university: University;
	buddyTeam: BuddyTeam;
	user: UserModel;
};

let data: LeadModel[] = [
  {
    leadId: 1,
    university: {
      universityId: 1,
      latitude: 123.456,
      longitude: 789.012,
      name: "Example University",
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
    },
    buddyTeam: {
      buddyTeamId: 1,
      facilitator: {
        facilitatorId: 1,
        university: {
          universityId: 1,
          latitude: 123.456,
          longitude: 789.012,
          name: "Example University",
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
        },
        user: {
          userId: 1,
          name: "John",
          surname: "Doe",
          email: "john.doe@example.com",
          username: "johndoe",
          userType: UserType.Facilitator,
          profileImage: "john-doe.png",
          phoneNumber: "1234567890",
          biography: "Lorem ipsum dolor sit amet...",
          interests: []
        },
      },
      leads: [],
    },
    user: {
      userId: 1,
      name: "John",
      surname: "Doe",
      email: "john.doe@example.com",
      username: "johndoe",
      userType: UserType.Lead,
      profileImage: "john-doe.png",
      phoneNumber: "1234567890",
      biography: "Lorem ipsum dolor sit amet...",
      interests: []
    },
  },
];


const LeadTable = ({axiosSecure}: LeadTableProps) => {

	const {
		data: allLeads,
		isLoading: isLeadsLoading,
		// isError: isLeadsError,
	} = useGetLeads(axiosSecure);

  if (allLeads) {
    data = allLeads.data!;
  } else {
    data = [];
  }

  const columns = useMemo<MRT_ColumnDef<LeadModel>[]>(
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
                <Image
                  alt="avatar"
                  height={40}
                  width={40}
                  src={row.original.user.profileImage}
                  radius="xl"
                  withPlaceholder
                  placeholder={
                    <Image
                      height={40}
                      width={40}
                      radius="xl"
                      src={DefaultProfilePicture}
                    />
                  }
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
        id: 'lead',
        header: 'Lead',
        columns: [
          {
            accessorKey: 'university.name',
            header: 'University',
            size: 200,
            filterVariant: 'autocomplete',
          },
          {
            accessorKey: 'university.city.name',
            header: 'City',
            size: 200,
            filterVariant: 'autocomplete',
          },
          {
            accessorKey: 'university.country.name',
            header: 'Country',
            size: 200,
            filterVariant: 'autocomplete',
          },
          {
            accessorKey: 'buddyTeam.facilitator.user.name',
            header: 'Facilitator',
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


	if (isLeadsLoading || !allLeads) {
		return <LoadingPage />;
	}
  return <MantineReactTable table={table} />;
};

export default LeadTable;