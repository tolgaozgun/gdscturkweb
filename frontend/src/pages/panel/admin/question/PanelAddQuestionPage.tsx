
import { Center } from '@mantine/core';
import AddQuestionForm from '../../../../components/forms/panel/question/AddQuestionForm';
import { PageContainer } from '../../../../components/PageContainer';

const PanelAddQuestionPage = () => {
	return (
		<PageContainer title="Add Question">
			<AddQuestionForm padding={10} />
		</PageContainer>
	);
};

export default PanelAddQuestionPage;
