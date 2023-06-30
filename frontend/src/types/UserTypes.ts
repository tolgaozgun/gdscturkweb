import { Topic } from "./InfoTypes";
import { City, Country } from "./LocationTypes";
import { University } from "./UniversityTypes";

export enum UserType {
	Admin = 'ADMIN',
	Lead = 'LEAD',
	CoreTeamMember = 'CORE_TEAM_MEMBER',
	Facilitator = 'FACILITATOR',
	Googler = 'GOOGLER'
}

export type User = {
	id: number;
	name: string;
	surname: string;
	email: string;
	username: string;
	userType: UserType;
	accessToken: string;
	refreshToken: string;
};

export type BuddyTeam = {
	buddyTeamId: number;
	facilitator: FacilitatorModel;
	leads: Array<LeadModel>;
}

export type UserModel = {
	userId: number;
	name: string;
	surname: string;
	email: string;
	username: string;
	userType: UserType;
	profileImage: string;
	phoneNumber: string;
	biography: string;
	interests: Array<Topic>;
};

export type LeadModel = {
	leadId: number;
	university: University;
	buddyTeam: BuddyTeam;
	user: UserModel;
};

export type GooglerModel = {
	googlerId: number;
	city: City,
	country: Country,
	user: UserModel;
}

export type CoreTeamMemberModel = {
	coreTeamMemberId: number;
	university: University;
	user: UserModel;
}

export type FacilitatorModel = {
	facilitatorId: number;
	university: University;
	user: UserModel;
}

// String models

