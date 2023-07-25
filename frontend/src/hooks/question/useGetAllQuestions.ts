import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { getAllQuestions } from '../../services/question/QuestionService';

const useGetAllQuestions = (axiosSecure: AxiosInstance) => {
	return useQuery({
		queryKey: ['getAllQuestions'],
		queryFn: () => getAllQuestions(axiosSecure),
	});
};

export default useGetAllQuestions;
