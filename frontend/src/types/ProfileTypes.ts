import { Topic } from "./TopicTypes";


export type UpdateUserProfileByUser = {
    name?: string;
    surname?: string;
    profileImage?: string;
    phoneNumber?: string;
    biography?: string;
    interests?: Array<Topic>;
}

export type UpdateUserProfileByStaff = {
    name?: string;
    surname?: string;
    email?: string;
    profileImage?: string;
    phoneNumber?: string;
    biography?: string;
    interests?: Array<Topic>;
}

export type UpdateLeadProfileByStaff = {
    universityId?: number;
    buddyTeamId?: number;
}

export type UpdateLeadProfileByLead = {

}

export type UpdateGooglerProfileByStaff = {
    cityId?: number;
    countryId?: number;
}

export type UpdateGooglerProfileByGoogler = {}

export type UpdateFacilitatorProfileByStaff = {
    universityId?: number;
}

export type UpdateFacilitatorProfileByFacilitator = {}


export type UpdateCoreTeamMemberProfileByStaff = {
    universityId?: number;
}

export type UpdateCoreTeamMemberProfileByCoreTeam = {}



export type UpdateLeadProfileByLeadRequest = {
    lead: UpdateLeadProfileByLead;
    user: UpdateUserProfileByUser;
}

export type UpdateLeadProfileByStaffRequest = {
    lead: UpdateLeadProfileByStaff;
    user: UpdateUserProfileByStaff;
}

export type UpdateGooglerProfileByGooglerRequest = {
    googler: UpdateGooglerProfileByGoogler;
    user: UpdateUserProfileByUser;
}

export type UpdateGooglerProfileByStaffRequest = {
    googler: UpdateGooglerProfileByStaff;
    user: UpdateUserProfileByStaff;
}

export type UpdateFacilitatorProfileByFacilitatorRequest = {
    facilitator: UpdateFacilitatorProfileByFacilitator;
    user: UpdateUserProfileByUser;
}

export type UpdateFacilitatorProfileByStaffRequest = {
    facilitator: UpdateFacilitatorProfileByStaff;
    user: UpdateUserProfileByStaff;
}

export type UpdateCoreTeamMemberProfileByCoreTeamRequest = {
    coreTeamMember: UpdateCoreTeamMemberProfileByCoreTeam;
    user: UpdateUserProfileByUser;
}

export type UpdateCoreTeamMemberProfileByStaffRequest = {
    coreTeamMember: UpdateCoreTeamMemberProfileByStaff;
    user: UpdateUserProfileByStaff;
}

export type UpdateUserProfileByUserRequest = {
    user: UpdateUserProfileByUser;
}

export type UpdateUserProfileByStaffRequest = {
    user: UpdateUserProfileByStaff;
}

export type SocialMediaLinks = {
    facebook?: string;
    instagram?: string;
    twitter?: string;
    youtube?: string;
    linkedin?: string;
    github?: string;
    medium?: string;
    discord?: string;
    others?: Array<string>;

}