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

export type UserModel = {
	userId: number;
	name: string;
	surname: string;
	email: string;
	username: string;
	userType: UserType;
};
