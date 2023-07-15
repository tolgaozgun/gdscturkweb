import { UserModel } from "./UserTypes";

export type Question = {
    questionId: number;
    title: string;
    question: string;
    answer?: string;
    askedBy: UserModel;
    answeredBy?: UserModel;
    askedDate: Date;
    answeredDate?: Date;
    category: QuestionCategory;
}

export type QuestionCategory = {
    questionCategoryId: number;
    name: string;
    image: string;
    shortUrl: string;
}
    
export type AnswerQuestion = {
    questionId: number;
    answer: string;
}

export type AskQuestion = {
    question: string;
}
