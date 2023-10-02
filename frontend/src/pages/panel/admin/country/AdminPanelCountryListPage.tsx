import { Box, Text, Title, Button, Menu } from '@mantine/core';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { MRT_ColumnDef, MRT_Row, MRT_TableInstance } from 'mantine-react-table';
import { useMemo } from 'react';
import LoadingPage from '../../../LoadingPage';
import BaseTable from '../../../../components/table/BaseTable';
import { IconSend, IconUserCircle } from '@tabler/icons-react';
import { Country } from '../../../../types/CountryTypes';
import useGetCountries from '../../../../hooks/country/useGetCountries';
import { PageContainer } from '../../../../components/PageContainer';

type PageType = Country

const AdminPanelCountryListPage = () => {
	const axiosSecure = useAxiosSecure();

	const {
		data: allCountries,
		isLoading: isCountriesLoading,
	} = useGetCountries(axiosSecure);

	const columns = useMemo<MRT_ColumnDef<PageType>[]>(
		() => [
		  {
			id: 'country', //id used to define `group` column
			header: 'Country',
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
					<span>{renderedCellValue}</span>
				  </Box>
				),
			  },
			  {
				accessorFn: (row) => `${row.flagImage}`, //accessorFn used to join multiple data into a single cell
				enableClickToCopy: true,
				header: 'Flag Image',
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
				<Box sx={{ textAlign: 'center' }}>
				<Title>Biography:</Title>
				<Text>&quot;{props.row.original.name}&quot;</Text>
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

	if (isCountriesLoading || !allCountries) {
		return <LoadingPage />
	}

	return (

		<PageContainer title="Country List">
			<BaseTable 
				data={allCountries?.data!} 
				columns={columns} 
				renderDetailPanel={renderDetailPanel}
				renderTopToolbarCustomActions={renderTopToolbarCustomActions}
				rowActionMenuItems={rowActionMenuItems}
				  />
		</PageContainer>
	);
};

export default AdminPanelCountryListPage;
