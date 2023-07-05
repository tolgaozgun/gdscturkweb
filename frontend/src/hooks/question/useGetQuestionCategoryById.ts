import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { GetQuestionCategoryById } from '../../types/QuestionTypes';
import { getQuestionCategoryById } from '../../services/question/QuestionService';

const useGetQuestionCategoryById = (axiosSecure: AxiosInstance, getQuestionCategoryByIdRequest: GetQuestionCategoryById) => {
	return useQuery({
		queryKey: ['getQuestionById'],
		queryFn: () => getQuestionCategoryById(axiosSecure, getQuestionCategoryByIdRequest),
	});
};

export default useGetQuestionCategoryById;
