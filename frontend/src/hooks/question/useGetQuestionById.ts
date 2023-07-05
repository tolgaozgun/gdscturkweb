import { AxiosInstance } from 'axios';
import { useQuery } from '@tanstack/react-query';
import { GetQuestionById } from '../../types/QuestionTypes';
import { getQuestionById } from '../../services/question/QuestionService';

const useGetQuestionById = (axiosSecure: AxiosInstance, getQuestionByIdRequest: GetQuestionById) => {
	return useQuery({
		queryKey: ['getQuestionById'],
		queryFn: () => getQuestionById(axiosSecure, getQuestionByIdRequest),
	});
};

export default useGetQuestionById;
