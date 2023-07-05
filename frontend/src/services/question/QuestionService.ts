import { baseUrl } from '../../constants/api';
import { AnswerQuestion, AskQuestion, GetQuestionById, GetQuestionCategoryById, GetQuestionsAnsweredBy, GetQuestionsAskedAnsweredBy, GetQuestionsAskedBy, Question, QuestionCategory, getQuestionsByCategory } from '../../types/QuestionTypes';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';

export async function getAllQuestions(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions`);
	return res.data;
}

export async function getQuestionsByCategory(axiosSecure: AxiosInstance, getQuestion: getQuestionsByCategory) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions/by-category`, {params: getQuestion});
	return res.data;
}

export async function getAllQuestionCategories(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<QuestionCategory>>>(`${baseUrl}/questions/categories`);
	return res.data;
}

export async function getQuestionCategoryById(axiosSecure: AxiosInstance, getQuestion: GetQuestionCategoryById) {
	const res = await axiosSecure.get<Response<Array<QuestionCategory>>>(`${baseUrl}/questions/categories/by-id`, {params: getQuestion});
	return res.data;
}

export async function getQuestionById(axiosSecure: AxiosInstance, getQuestion: GetQuestionById) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions/by-id`, {params: getQuestion});
	return res.data;
}

export async function getQuestionsAskedByUser(axiosSecure: AxiosInstance, getQuestion: GetQuestionsAskedBy) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions/asked-by-user`, {params: getQuestion});
	return res.data;
}

export async function getQuestionsAnsweredByUser(axiosSecure: AxiosInstance, getQuestion: GetQuestionsAnsweredBy) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions/answered-by-user`, {params: getQuestion});
	return res.data;
}

export async function getQuestionsAskedAnsweredByUser(axiosSecure: AxiosInstance, getQuestion: GetQuestionsAskedAnsweredBy) {
	const res = await axiosSecure.get<Response<Array<Question>>>(`${baseUrl}/questions/asked-answered-by-user`, {params: getQuestion});
	return res.data;
}

export async function askQuestion(axiosSecure: AxiosInstance, askQuestion: AskQuestion) {
	const res = await axiosSecure.post<Response<Question>>(`${baseUrl}/questions/ask`, askQuestion);
	return res.data;
}

export async function answerQuestion(axiosSecure: AxiosInstance, answerQuestion: AnswerQuestion) {
	const res = await axiosSecure.post<Response<Question>>(`${baseUrl}/questions/answer`, answerQuestion);
	return res.data;
}

