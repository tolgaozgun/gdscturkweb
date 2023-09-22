import { UserType } from ".";

export type InviteUserRequest = {
    email: string;
    role: UserType;
}

export type UserInvitationResponse = {
    email: string;
    role: UserType;
    validUntil: Date;
    isValid?: boolean;
}

