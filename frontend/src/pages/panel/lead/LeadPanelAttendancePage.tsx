import { Center } from '@mantine/core';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { PageContainer } from '../../../components/PageContainer';
import useGetCurrentLeadAttendance from '../../../hooks/attendance/useGetCurrentLeadAttendance';
import LeadAttendanceTable from '../../../components/table/LeadAttendanceTable';
import LoadingPage from '../../LoadingPage';
import { LeadAttendance } from '../../../types/AttendaceTypes';



const LeadPanelAttendancePage = () => {
	const axiosSecure = useAxiosSecure();

	let {
		data: attendanceData,
		isLoading: attendanceLoading,
	} = useGetCurrentLeadAttendance(axiosSecure);

	let attendance: LeadAttendance[] = []

	if (attendanceData && attendanceData?.data){
		attendance = attendanceData?.data!
	}

	if (attendanceLoading || !attendanceData) { 
		return <LoadingPage />
	}

	return (
		<PageContainer title="Attendance List">
			<Center mt="md">
				<LeadAttendanceTable data={attendance} isLoading={attendanceLoading} />
			</Center>
		</PageContainer>
	);
};

export default LeadPanelAttendancePage;
