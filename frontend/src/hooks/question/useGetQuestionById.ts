import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getQuestionById } from '../../services/question/QuestionService';

const useGetQuestionById = (axiosSecure: AxiosInstance, questionId: number) => {
	return useQuery({
		queryKey: ['getQuestionById'],
		queryFn: () => getQuestionById(axiosSecure, questionId),
	});
};

export default useGetQuestionById;
