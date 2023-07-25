import { Box, Text, Title, Button, Menu, Image } from '@mantine/core';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { MRT_ColumnDef, MRT_Row, MRT_TableInstance } from 'mantine-react-table';
import { useMemo } from 'react';
import LoadingPage from '../../../LoadingPage';
import BaseTable from '../../../../components/table/BaseTable';
import { IconSend, IconUserCircle } from '@tabler/icons-react';
import { PageContainer } from '../../../../components/PageContainer';
import useGetAllQuestions from '../../../../hooks/question/useGetAllQuestions';
import { Question } from '../../../../types/QuestionTypes';
import DefaultProfilePicture from "../../../../assets/default_profile_picture.png";


type PageType = Question

const PanelQuestionsListPage = () => {
	const axiosSecure = useAxiosSecure();

	const {
		data: allQuestions,
		isLoading: isQuestionsLoading,
	} = useGetAllQuestions(axiosSecure);

	const columns = useMemo<MRT_ColumnDef<PageType>[]>(
		() => [
		  {
			id: 'question', //id used to define `group` column
			header: 'Question',
			columns: [
			  {
				accessorFn: (row) => `${row.category.name}`, //accessorFn used to join multiple data into a single cell
				id: 'category', //id is still required when using accessorFn instead of accessorKey
				header: 'Category',
				size: 250,
				filterVariant: 'autocomplete',
			  },
			  {
				accessorKey: 'title', //accessorKey used to define `data` column. `id` gets set to accessorKey automatically
				enableClickToCopy: true,
				header: 'Question',
				size: 300,
			  },
			  {
				accessorFn: (row) => row.answer ? "Answered" : "Not answered",
				enableClickToCopy: true,
				header: 'Answer',
				size: 300,
			  },
			],
		  },
		  {
			id: 'user',
			header: 'User',
			columns: [
			  {
				accessorFn: (row) => `${row.askedBy.name} ${row.askedBy.surname}`, //accessorFn used to join multiple data into a single cell
				id: 'askedBy', //id is still required when using accessorFn instead of accessorKey
				header: 'Asked By',
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
					  src={row.original.askedBy.profileImage}
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
				accessorFn: (row) => `${row.answeredBy?.name} ${row.answeredBy?.surname}`, //accessorFn used to join multiple data into a single cell
				id: 'answeredBy', //id is still required when using accessorFn instead of accessorKey
				header: 'Answered By',
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
					  src={row.original.answeredBy?.profileImage}
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
					<Title>{props.row.original.question}</Title>
					<Text>&quot;{props.row.original.answer}&quot;</Text>
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

	if (isQuestionsLoading || !allQuestions) {
		return <LoadingPage />
	}

	return (

		<PageContainer title="Questions List">
			<BaseTable 
				data={allQuestions?.data!} 
				columns={columns} 
				renderDetailPanel={renderDetailPanel}
				renderTopToolbarCustomActions={renderTopToolbarCustomActions}
				rowActionMenuItems={rowActionMenuItems}
				  />
		</PageContainer>
	);
};

export default PanelQuestionsListPage;
