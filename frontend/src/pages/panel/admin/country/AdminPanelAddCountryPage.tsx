
import AddCountryForm from '../../../../components/forms/panel/country/AddCountryForm';
import { PageContainer } from '../../../../components/PageContainer';

const AdminPanelAddCountryPage = () => {
	return (

		<PageContainer title="Add Country">
			<AddCountryForm padding={20} mt={10} />
		</PageContainer>
	);
};

export default AdminPanelAddCountryPage;
