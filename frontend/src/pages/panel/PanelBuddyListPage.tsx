import { Center } from '@mantine/core';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import BuddyTeamTable from '../../components/table/BuddyTeamTable';



const PanelBuddyTeamListPage = () => {
	const axiosSecure = useAxiosSecure();

	return (
		<Center>
			<BuddyTeamTable axiosSecure={axiosSecure} />
		</Center>
	);
};

export default PanelBuddyTeamListPage;
