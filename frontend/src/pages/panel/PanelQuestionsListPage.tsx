import { Center } from '@mantine/core';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import PanelQuestionsTable from '../../components/table/panel/PanelQuestionsTable';



const PanelQuestionsListPage = () => {
	const axiosSecure = useAxiosSecure();

	return (
		<Center>
			<PanelQuestionsTable axiosSecure={axiosSecure} />
		</Center>
	);
};

export default PanelQuestionsListPage;
