import { Alert, Center } from '@mantine/core';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { PageContainer } from '../../../components/PageContainer';
import useGetCurrentLeadAttendance from '../../../hooks/attendance/useGetCurrentLeadAttendance';
import LeadAttendanceTable from '../../../components/table/LeadAttendanceTable';
import LoadingPage from '../../LoadingPage';
import { LeadAttendance } from '../../../types/AttendaceTypes';
import { IconExclamationMark } from '@tabler/icons-react';



const LeadPanelAttendancePage = () => {
	const axiosSecure = useAxiosSecure();

	let {
		data: attendanceData,
		isLoading: attendanceLoading,
	} = useGetCurrentLeadAttendance(axiosSecure);

	let attendance: LeadAttendance[] = []

	let attendanceExists = false;

	if (attendanceData && attendanceData?.data && attendanceData!.data!.length > 0){
		attendance = attendanceData?.data!
		attendanceExists = true;
	}

	if (attendanceLoading || !attendanceData) { 
		return <LoadingPage />
	}

	let content = <Alert variant='outline' color="red" title="Error" icon={<IconExclamationMark/>}>Attendance information not found. You may be not invited to the buddy team. If you believe this is an error please contant administrator.</Alert>;

	if (attendanceExists) {
		content = (
			<Center mt="md">
				<LeadAttendanceTable data={attendance} isLoading={attendanceLoading} />
			</Center>
		)
	}
	

	return (
		<PageContainer title="Attendance List">
			{content}
		</PageContainer>
	);
};

export default LeadPanelAttendancePage;
