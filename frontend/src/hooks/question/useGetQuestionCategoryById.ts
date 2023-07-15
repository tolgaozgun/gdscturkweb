import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getQuestionCategoryById } from '../../services/question/QuestionService';

const useGetQuestionCategoryById = (axiosSecure: AxiosInstance, categoryId: number) => {
	return useQuery({
		queryKey: ['getQuestionById'],
		queryFn: () => getQuestionCategoryById(axiosSecure, categoryId),
	});
};

export default useGetQuestionCategoryById;
