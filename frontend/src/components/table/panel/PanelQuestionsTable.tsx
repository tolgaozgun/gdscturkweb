import { useMemo } from 'react';
import {
  MantineReactTable,
  useMantineReactTable,
  type MRT_ColumnDef,
} from 'mantine-react-table';
import { Box, Button, Menu, Text, Title, Image } from '@mantine/core';
import { IconUserCircle, IconSend } from '@tabler/icons-react';
import { AxiosInstance } from 'axios';
import DefaultProfilePicture from "../../../assets/default_profile_picture.png";
import { Question } from '../../../types/QuestionTypes';
import LoadingPage from '../../../pages/LoadingPage';
import { UserType } from '../../../types';
import useGetAllQuestions from '../../../hooks/question/useGetAllQuestions';

interface PanelQuestionsTableProps {
  axiosSecure: AxiosInstance
}


let data: Question[] = [
  {
    questionId: 1,
    title: "What is the capital of France?",
    question: "What is the capital of France?",
    answer: "The capital of France is Paris.",
    askedBy: {
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
    answeredBy: {
      userId: 2,
      name: "Jane",
      surname: "Smith",
      email: "jane.smith@example.com",
      username: "janesmith",
      userType: UserType.Lead,
      profileImage: "jane-smith.png",
      phoneNumber: "9876543210",
      biography: "Lorem ipsum dolor sit amet...",
      interests: []
    },
    askedDate: new Date("2022-01-01"),
    answeredDate: new Date("2022-01-02"),
    category: {
      questionCategoryId: 1,
      name: "General Knowledge",
      image: "knowledge.png",
      shortUrl: "general",
    },
  },
  // Additional Question objects...
];



const PanelQuestionsTable = ({axiosSecure}: PanelQuestionsTableProps) => {

	const {
		data: allQuestions,
		isLoading: isQuestionsLoading,
		// isError: isLeadsError,
	} = useGetAllQuestions(axiosSecure);

  if (allQuestions) {
    data = allQuestions.data!;
  } else {
    data = [];
  }

  const columns = useMemo<MRT_ColumnDef<Question>[]>(
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
          src={row.original.user.profileImage}
          style={{ borderRadius: '50%' }}
        /> */}
        <Box sx={{ textAlign: 'center' }}>
          <Title>{row.original.question}</Title>
          <Text>&quot;{row.original.answer}&quot;</Text>
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


	if (isQuestionsLoading || !allQuestions) {
		return <LoadingPage />;
	}
  return <MantineReactTable table={table} />;
};

export default PanelQuestionsTable;