import { Box, Text, Title, Button, Menu, Modal } from '@mantine/core';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { MRT_ColumnDef, MRT_Row, MRT_TableInstance } from 'mantine-react-table';
import { useMemo } from 'react';
import LoadingPage from '../../../LoadingPage';
import BaseTable from '../../../../components/table/BaseTable';
import { IconSend, IconUserCircle } from '@tabler/icons-react';
import { PageContainer } from '../../../../components/PageContainer';
import { Activity } from '../../../../types/EventTypes';
import useGetEventsByCurrentUserUniversity from '../../../../hooks/event/useGetEventsByCurrentUserUniversity';
import { useParseCurrentUserEvents } from '../../../../hooks/event/useParseCurrentUserEvents';
import { isErrorResponse } from '../../../../utils/utils';
import { notifications } from '@mantine/notifications';
import { useDisclosure } from '@mantine/hooks';
import LoadingLottie from '../../../../components/common/other/LoadingLottie';
import { useNavigate } from 'react-router';

type PageType = Activity

const LeadPanelMyEventsPage = () => {
	const axiosSecure = useAxiosSecure();
    const {parse} = useParseCurrentUserEvents(axiosSecure);
    const navigate = useNavigate();

    const [opened, { open, close }] = useDisclosure(false);
    
	const {
		data: allEvents,
		isLoading: isEventsLoading,
	} = useGetEventsByCurrentUserUniversity(axiosSecure);

    const handleParseNewEvents = async () => {
        open();
        const res = await parse();
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'parse-fail',
				title: 'Parse failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			return;
		}

		notifications.show({
			id: 'parse-success',
			title: 'Parse successful!',
			message:
				'You have successfully parsed new events!',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
			styles: (theme) => ({
				title: { color: theme.white },
				description: { color: theme.white }
			})
		});
        close();
        navigate(0);
    }

	const columns = useMemo<MRT_ColumnDef<PageType>[]>(
		() => [
		  {
			id: 'event', //id used to define `group` column
			header: 'Event',
			columns: [
			  {
				accessorFn: (row) => `${row.title}`, //accessorFn used to join multiple data into a single cell
				id: 'title', //id is still required when using accessorFn instead of accessorKey
				header: 'Title',
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
				accessorFn: (row) => `${row.startDate}`, //accessorFn used to join multiple data into a single cell
				enableClickToCopy: true,
				header: 'Start Date',
				size: 300,
			  },
			  {
				accessorFn: (row) => `${row.endDate}`, //accessorFn used to join multiple data into a single cell
				enableClickToCopy: true,
				header: 'End Date',
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
				<Title>Title:</Title>
				<Text>&quot;{props.row.original.title}&quot;</Text>
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
				<Menu.Item icon={<IconUserCircle />}>View Details</Menu.Item>
				<Menu.Item icon={<IconSend />}>Send Email</Menu.Item>
			</>
		)
	}

	if (isEventsLoading || !allEvents) {
		return <LoadingPage />
	}

	return (
        <>
        <Modal opened={opened} onClose={close} title="Parsing..">
            Please wait while parsing...
        </Modal>
		<PageContainer title="Events">
            <Button mb="md" onClick={handleParseNewEvents}>
                Parse New Events
            </Button>
			<BaseTable 
				data={allEvents?.data!} 
				columns={columns} 
				renderDetailPanel={renderDetailPanel}
				renderTopToolbarCustomActions={renderTopToolbarCustomActions}
				rowActionMenuItems={rowActionMenuItems}
				  />
		</PageContainer>
        </>
	);
};

export default LeadPanelMyEventsPage;
