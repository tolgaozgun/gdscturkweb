import { Accordion, Center } from '@mantine/core';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import { PageContainer } from '../../../components/PageContainer';
import LoadingPage from '../../LoadingPage';
import { Attendance, AttendanceStatus } from '../../../types/AttendaceTypes';
import useGetBuddyTeamAttendanceByFacilitator from '../../../hooks/attendance/useGetBuddyTeamAttendanceByFacilitator';
import { LeadModel } from '../../../types';
import AttendanceTable from '../../../components/table/AttendanceTable';



const FacilitatorPanelAttendancePage = () => {
	const axiosSecure = useAxiosSecure();

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

	return (
		<PageContainer title="Attendance List">
			<Center mt="md">
				<Accordion>
				{attendanceStatus.map((attendance) => {
					return (
						<Accordion.Item value={attendance.date.toString()}>
							<Accordion.Control>attendence.date.toString()</Accordion.Control>
							<Accordion.Panel>
								<AttendanceTable data={attendance.status} isLoading={attendanceLoading} />
							</Accordion.Panel>
						</Accordion.Item>
					)
				})}
				</Accordion>
			</Center>
		</PageContainer>
	);
};

export default FacilitatorPanelAttendancePage;
