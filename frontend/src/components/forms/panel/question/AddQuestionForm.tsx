import {
	Affix,
	Card,
	Flex,
	Input,
	Title,
	rem,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { isErrorResponse } from '../../../../utils/utils';
import CustomElevatedButton from '../../../buttons/CustomElevatedButton';
import { AskQuestion } from '../../../../types/QuestionTypes';
import { askQuestion } from '../../../../services/question/QuestionService';
import CustomTextEditor from '../../CustomTextEditor';

interface AddQuestionFormProps {
	padding?: number;
	mt?: number;
}

const AddQuestionForm = ({padding, mt}: AddQuestionFormProps) => {
	const axiosSecure = useAxiosSecure();

	const form = useForm({
		initialValues: {
			question: ""
		},
		validate: {
			question: (value) => (value.trim().length > 0 ? null : 'Question is required'),
		},
	});

	const { mutateAsync: addQuestion } = useMutation({
		mutationKey: ['addQuestion'],
		mutationFn: (addQuestion: AskQuestion) => askQuestion(axiosSecure, addQuestion),
		onError: (error: any) =>
			notifications.show({
				id: 'question-create-fail',
				title: 'Asking Question failed!',
				message: error.response ? error.response.data.msg : 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});



	const handleAddQuestion = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const questionDetails: AskQuestion = {
			question: form.values.question,
		};

		const res = await addQuestion(questionDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't add the city",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'University added successfully.',
				color: 'green',
			});
		}
	};


	return (
		<Flex direction={'column'} gap={'xs'} p={padding} mt={mt}>
			<form>
				<Flex direction={'column'} gap={'xs'}>
					<Input.Wrapper
						withAsterisk
						label="Question"
						{...form.getInputProps('question')}
					>
						<CustomTextEditor />
					</Input.Wrapper>
				</Flex>
			</form>
			<Affix position={{ bottom: rem(20), right: rem(20) }}>	
				<Flex gap="md">
					<CustomElevatedButton text={'Cancel'} color="red" />
					<CustomElevatedButton text={'Add Question'} onClick={handleAddQuestion} />
				</Flex>		
			</Affix>
		</Flex>
	);
};

export default AddQuestionForm;
