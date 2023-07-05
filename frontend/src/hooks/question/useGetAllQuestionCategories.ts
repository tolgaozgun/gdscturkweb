import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllQuestionCategories } from '../../services/question/QuestionService';

const useGetAllQuestionCategories = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllQuestionCategories'],
		queryFn: () => getAllQuestionCategories(axiosSecure),
	});
};

export default useGetAllQuestionCategories;
