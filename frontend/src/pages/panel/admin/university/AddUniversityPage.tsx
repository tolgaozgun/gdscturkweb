
import { Center } from '@mantine/core';
import AddUniversityForm from '../../../../components/forms/panel/university/AddUniversityForm';
import { PageContainer } from '../../../../components/PageContainer';

const AddUniversityPage = () => {
	return (
		<PageContainer title="Add University">
			<AddUniversityForm padding={10} />
		</PageContainer>
	);
};

export default AddUniversityPage;
