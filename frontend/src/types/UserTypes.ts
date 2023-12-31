import { Topic } from "./TopicTypes";
import { City } from "./CityTypes";
import { University } from "./UniversityTypes";
import { Country } from "./CountryTypes";
import { BuddyTeam } from "./BuddyTeamTypes";




export enum UserType {
	Admin = 'ADMIN',
	Lead = 'LEAD',
	CoreTeamMember = 'CORE_TEAM_MEMBER',
	Facilitator = 'FACILITATOR',
	Googler = 'GOOGLER',
}

export type Token = {
	accessToken: string;
	refreshToken: string;
}

export type User = {
	id: number;
	name: string;
	surname: string;
	email: string;
	username: string;
	userType: UserType;
	profileImage: string;
	phoneNumber: string;
	biography: string;
	interests: Array<Topic>;
	accessToken: string;
	refreshToken: string;
};

export type UserWithRole = {
	user: User;
	extra: Object;
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
	lastLoginDate: Date;
	createdAt: Date;
	lastEditedAt: Date;
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

export type LeadDashboardResponse = {
	lead: LeadModel;
	buddyTeamSize: number;
	coreTeamSize: number;
	buddyMeetings: Map<Date, boolean>;
	promotedAt?: Date;
}

export type CoreTeamMemberDashboardResponse = {
	coreTeamMember: CoreTeamMemberModel;
	coreTeamSize: number;
	promotedAt?: Date;
}

export type FacilitatorDashboardResponse = {
	facilitator: FacilitatorModel;
	buddyTeamSize: number;
	buddyMeetings: Map<Date, boolean>;
	promotedAt?: Date;
}
