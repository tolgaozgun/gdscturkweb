
import { Center } from '@mantine/core';
import AddCountryForm from '../../../../components/forms/panel/country/AddCountryForm';
import { PageContainer } from '../../../../components/PageContainer';

const AddCountryPage = () => {
	return (

		<PageContainer title="Add Country">
			<AddCountryForm padding={20} mt={10} />
		</PageContainer>
	);
};

export default AddCountryPage;
