import { baseUrl } from '../../constants/api';
import { Response } from '../../types/ResponseTypes';
import { AxiosInstance } from 'axios';
import { CreateTopic, Topic, UpdateTopic } from '../../types/TopicTypes';

export async function getAllTopics(axiosSecure: AxiosInstance) {
	const res = await axiosSecure.get<Response<Array<Topic>>>(`${baseUrl}/topics`);
	return res.data;
}

export async function getTopicById(axiosSecure: AxiosInstance, topicId: number) {
	const res = await axiosSecure.get<Response<Topic>>(`${baseUrl}/topics/${topicId}`);
	return res.data;
}

export async function createTopic(axiosSecure: AxiosInstance, createTopicRequest: CreateTopic) {
    const res = await axiosSecure.post<Response<Topic>>(`${baseUrl}/topics/create`, createTopicRequest);
    return res.data;
}

export async function editTopic(axiosSecure: AxiosInstance, topicId: number, editTopicRequest: UpdateTopic) {
    const res = await axiosSecure.post<Response<Topic>>(`${baseUrl}/topics/edit/${topicId}`, editTopicRequest);
    return res.data;
}