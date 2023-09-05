import { Accordion, Button, Center } from '@mantine/core';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { PageContainer } from '../../../../components/PageContainer';
import LoadingPage from '../../../LoadingPage';
import { Attendance, AttendanceStatus } from '../../../../types/AttendaceTypes';
import useGetBuddyTeamAttendanceByFacilitator from '../../../../hooks/attendance/useGetBuddyTeamAttendanceByFacilitator';
import { LeadModel } from '../../../../types';
import AttendanceTable from '../../../../components/table/AttendanceTable';
import { Text } from "@mantine/core"
import SubtleLinkButton from '../../../../components/buttons/SubtleLinkButton';
import { useNavigate } from 'react-router';



const FacilitatorPanelAttendancePage = () => {
	const axiosSecure = useAxiosSecure();
	const navigate = useNavigate();

	let facilitatorId = 1;

	let {
		data: attendanceData,
		isLoading: attendanceLoading,
	} = useGetBuddyTeamAttendanceByFacilitator(axiosSecure, facilitatorId);

	let attendance: Attendance[] = [];
	let attendanceStatus = [];

	// Convert Map<LeadModel, boolean> to Array of dictionaries
	if (attendanceData && attendanceData?.data){
		attendance = attendanceData?.data!
		for(let [_, value] of attendance.entries()){
			let map: Map<LeadModel, boolean> = value.attendanceStatusMap;
			let date: Date = value.attendanceDate;

			let attendanceStatusArray: AttendanceStatus[] = [];
			for(let [key, value] of map.entries()){
				attendanceStatusArray.push({
					lead: key,
					attendanceStatus: value
				})
			}
			attendanceStatus.push({
				date: date,
				status: attendanceStatusArray
			})
		}
	}

	if (attendanceLoading || !attendanceData) { 
		return <LoadingPage />
	}

	let content;

	if (attendanceStatus.length > 0) {
		content = attendanceStatus.map((attendance) => {
			return (
				<Accordion.Item value={attendance.date.toString()}>
					<Accordion.Control>attendence.date.toString()</Accordion.Control>
					<Accordion.Panel>
						<AttendanceTable data={attendance.status} isLoading={attendanceLoading} />
					</Accordion.Panel>
				</Accordion.Item>
			)
		})
	} else {
		content = <Text>No attendance record found</Text>;
	}

	const buttonClickHandler = () => {
		navigate("/panel/facilitator/buddy-teams/attendance/add")
	}

	return (
		<PageContainer title="Attendance List">
		<Button onClick={buttonClickHandler} size="sm">
			Add Attendance
		</Button>
			<Center mt="md">
				<Accordion>
					{content}
				</Accordion>
			</Center>
		</PageContainer>
	);
};

export default FacilitatorPanelAttendancePage;
