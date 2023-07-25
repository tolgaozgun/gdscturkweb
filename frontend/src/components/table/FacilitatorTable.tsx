import { useMemo } from 'react';
import {
  type MRT_ColumnDef,
  MRT_Row,
  MRT_TableInstance,
} from 'mantine-react-table';
import { Box, Button, Menu, Text, Title } from '@mantine/core';
import { IconUserCircle, IconSend } from '@tabler/icons-react';
import { FacilitatorModel } from '../../types';
import LoadingPage from '../../pages/LoadingPage';
import BaseTable from './BaseTable';


type PageType = FacilitatorModel;

interface FacilitatorTableProps {
  data: PageType[];
  isLoading: boolean;
}

const FacilitatorTable = ({data, isLoading}: FacilitatorTableProps) => {

  const columns = useMemo<MRT_ColumnDef<FacilitatorModel>[]>(
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
        id: 'facilitator',
        header: 'Facilitator',
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
        ],
      },
    ],
    [],
  );


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
            src={props.row.original.user.profileImage}
            style={{ borderRadius: '50%' }}
          />
          <Box sx={{ textAlign: 'center' }}>
            <Title>Biography:</Title>
            <Text>&quot;{props.row.original.user.biography}&quot;</Text>
          </Box>
        </Box>
      )
    };



	const rowActionMenuItems = () => {
		return (
			<>
				<Menu.Item icon={<IconUserCircle />}>View Profile</Menu.Item>
				<Menu.Item icon={<IconSend />}>Send Email</Menu.Item>
			</>
		)
	}

	if (isLoading || !data) {
		return <LoadingPage />;
	}

  return (
			<BaseTable 
      data={data} 
      columns={columns} 
      renderDetailPanel={renderDetailPanel}
      renderTopToolbarCustomActions={renderTopToolbarCustomActions}
      rowActionMenuItems={rowActionMenuItems}
        />
  )
};

export default FacilitatorTable;