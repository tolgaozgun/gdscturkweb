import { UserType } from ".";

export type Attachment = {
    attachmentId: number;
    attachmentName: string;
    shortUrl: string;
    permittedUserTypes: Array<UserType>
}