import { UserType } from ".";
import { Attachment } from "./AttachmentTypes";
import { Question } from "./QuestionTypes";

export type Campaign = {
    campaignId: number;
    title: string;
    description: string;
    startDate: Date;
    endDate: Date;
    attachments: Array<Attachment>
    questions: Array<Question>;
    permittedUserTypes: Array<UserType>;
    campaignPages: Array<CampaignPage>;
}

export type CampaignPage = {
    campaignPageId: number;
    title: string;
    description: string;
    attachments: Array<Attachment>;
    permittedUserTypes: Array<UserType>;
}

export type CreateCampaignRequest = {
    title: string;
    description: string;
    startDate: Date;
    endDate: Date;
    attachmentIds?: Array<number>
    questionIds?: Array<number>;
    permittedUserTypes?: Array<UserType>;
    campaignPageIds?: Array<number>;
}

export type EditCampaignRequest = {
    title?: string;
    description?: string;
    startDate?: Date;
    endDate?: Date;
    attachmentIds?: Array<number>
    questionIds?: Array<number>;
    permittedUserTypes?: Array<UserType>;
    campaignPageIds?: Array<number>;
}

