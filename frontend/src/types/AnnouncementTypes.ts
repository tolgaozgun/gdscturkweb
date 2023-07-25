import { UserModel, UserType } from ".";

export type Announcement = {
    announcementId: number;
    title: string;
    description: string;
    startDate: Date;
    endDate: Date;
    sentBy: UserModel;
    permittedUserTypes: Array<UserType>;
}

export type CreateAnnouncement = {
    title: string;
    description: string;
    startDate: Date;
    endDate: Date;
    sentBy: UserModel;
    permittedUserTypes: Array<UserType>;
}

export type EditAnnouncement = {
    title?: string;
    description?: string;
    startDate?: Date;
    endDate?: Date;
    sentBy?: UserModel;
    permittedUserTypes?: Array<UserType>;
}

