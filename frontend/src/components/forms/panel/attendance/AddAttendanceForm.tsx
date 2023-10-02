import {
	Affix,
	Card,
	Flex,
	Group,
	NumberInput,
	Select,
	SelectItem,
	Table,
	Text,
	TextInput,
	Title,
	rem,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { IconFlag } from '@tabler/icons-react';
import { useMutation } from '@tanstack/react-query';
import { forwardRef, useMemo } from 'react';
import { Country } from '../../../../types/CountryTypes';
import { AddCity } from '../../../../types/CityTypes';
import { createCity } from '../../../../services/city/CItyService';
import useGetCountries from '../../../../hooks/country/useGetCountries';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../../pages/LoadingPage';
import { isErrorResponse } from '../../../../utils/utils';
import CustomElevatedButton from '../../../buttons/CustomElevatedButton';
import useGetBuddyTeamByCurrentLead from '../../../../hooks/buddy-team/useGetBuddyTeamByCurrentUser';
import useGetBuddyTeamByCurrentFacilitator from '../../../../hooks/buddy-team/useGetBuddyTeamByCurrentFacilitator';
import { BuddyTeam } from '../../../../types/BuddyTeamTypes';
import { AttendanceStatus, CreateAttendance } from '../../../../types/AttendaceTypes';
import { createAttendance } from '../../../../services/attendance/AttendanceService';
import { DateInput, DatePickerInput } from '@mantine/dates';
import { MRT_ColumnDef, MantineReactTable, useMantineReactTable } from 'mantine-react-table';
import { LeadModel } from '../../../../types';
import BaseTable from '../../../table/BaseTable';

export type TableAttendance = {
	lead: LeadModel;
	attendanceStatus: boolean;
};

interface AddAttendanceFormProps {
	padding?: number;
	mt?: number;
}

const AddAttendanceForm = ({padding, mt}: AddAttendanceFormProps) => {
	const axiosSecure = useAxiosSecure();

	const form = useForm({
		initialValues: {
			buddyTeamId: 0,
			attendanceDate: new Date(),
			// attendanceStatusMap: {},
		},
		validate: {
			buddyTeamId: (value) => (value > 0 ? null : 'Buddy team must be selected'),
			attendanceDate: (value) => (value ? null : 'Attendance date is required'),
			// attendanceStatusMap: (value) => (value ? null : 'Attendance status is required'),
		},
	});
	const {
		data: buddyTeamData,
		isLoading: isBuddyTeamLoading,
		isError: isBuddyTeamError,
	} = useGetBuddyTeamByCurrentFacilitator(axiosSecure);

	// const { mutateAsync: addAttendance } = useMutation({
	// 	mutationKey: ['addAttendance'],
	// 	mutationFn: (addAttendance: CreateAttendance) => createAttendance(axiosSecure, addAttendance),
	// 	onError: (error: any) =>
	// 		notifications.show({
	// 			id: 'attendance-create-fail',
	// 			title: 'Create Attendance failed!',
	// 			message: error.response ? error.response.data.msg : 'Something went wrong',
	// 			autoClose: 5000,
	// 			withCloseButton: true,
	// 			style: { backgroundColor: 'red' },
	// 			styles: (theme) => ({
	// 				title: { color: theme.white },
	// 				description: { color: theme.white }
	// 			})
	// 		}),
	// });


	const buddyTeam: BuddyTeam = buddyTeamData?.data!;
	// const countryData: Array<SelectItem> = buddyTeam.leads
	// 	.map((lead) => {
	// 		return {
	// 			name: lead.user.name + ' ' + lead.user.surname,
	// 			leadId: lead.leadId,
	// 			imag3: lead.user.profileImage,
	// 			value: String(lead.leadId),
	// 			label: lead.user.name + ' ' + lead.user.surname,
	// 		};
	// 	});
	const tableData: TableAttendance[] = buddyTeam?.leads?.map((lead) => {
		return {
			lead: lead,
			attendanceStatus: false,
		};
	});

	const handleAddCity = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		// const attendanceDetails: CreateAttendance = {
		// 	buddyTeamId: buddyTeamData.data!.buddyTeamId,
		// 	attendanceDate: form.values.attendanceDate,
		// 	attendanceStatusMap: 
		// 	// attendanceStatusMap: form.values.attendanceStatusMap,
		// };
		// const res = await addAttendance(attendanceDetails);
		// if (isErrorResponse(res)) {
		// 	notifications.show({
		// 		message: res?.msg || "Something went wrong. Couldn't add the attendance",
		// 		color: 'red',
		// 	});
		// } else {
		// 	notifications.show({
		// 		message: 'Attendance added successfully.',
		// 		color: 'green',
		// 	});
		// }
	};

	const columns = useMemo<MRT_ColumnDef<TableAttendance>[]>(
	  () => [
		{
		  accessorKey: 'lead.user.name', //access nested data with dot notation
		  header: 'First Name',
		  enableEditing: false,
		},
		{
		  accessorKey: 'lead.user.surname',
		  header: 'Last Name',
		  enableEditing: false,
		},
		{
		  accessorKey: 'attendanceStatus',
		  header: 'Attendance Status',
		  enableEditing: true,
		},
	  ],
	  [],
	);


	if (isBuddyTeamLoading || !buddyTeamData) {
		return <LoadingPage />;
	}
	if (isBuddyTeamError) {
		return <div>Error</div>;
	}


	return (
		<Flex direction={'column'} gap={'xl'} p={padding} mt={mt}>
			<form>
				<Flex direction={'column'} gap={'xl'}>
					<DatePickerInput
						withAsterisk
						label="Attendance Date"
						{...form.getInputProps('attendanceDate')}
					/>
					<BaseTable 
					  data={tableData} 
					  columns={columns}
					  enableEditing={true}
					/>
					{/* <Table>
						<tr>
							<td align="center">Lead</td>
							<td align="center">Attendance Status</td>
						</tr>
							{buddyTeam.leads.map((lead) => (
								<tr>
									<td align="center">
										<img src={lead.user.profileImage} height="50" width="50" />
									</td>
									<td align="center">

									</td>
								</tr>
								
					</Table>
					{/* <NumberInput
						withAsterisk
						label="Longitude"
						precision={6}
						{...form.getInputProps('longitude')}
					/> */}
				</Flex>
			</form>
			<Affix position={{ bottom: rem(20), right: rem(20) }}>	
				<Flex gap="md">
					<CustomElevatedButton text={'Cancel'} color="red" />
					<CustomElevatedButton text={'Add Attendance'} onClick={handleAddCity} />
				</Flex>		
			</Affix>
		</Flex>
	);
};

export default AddAttendanceForm;
