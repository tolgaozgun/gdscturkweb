import { UserModel } from "./UserTypes";

export type Question = {
    questionId: number;
    question: string;
    answer?: string;
    askedBy: UserModel;
    answeredBy?: UserModel;
    askedDate: Date;
    answeredDate?: Date;
}

export type AnswerQuestion = {
    questionId: number;
    answer: string;
}

export type AskQuestion = {
    question: string;
}

export type GetQuestion = {
    questionId: number;
}

export type GetQuestionsAnsweredBy = {
    userId: number;
}

export type GetQuestionsAskedBy = {
    userId: number;
}

export type GetQuestionsAskedAnsweredBy = {
    userId: number;
}