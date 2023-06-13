import { UserType } from './UserTypes';

export type Tokens = {
	accessToken: string;
	refreshToken: string;
};


export type UserModel = {
	userId: number;
	name: string;
	surname: string;
	email: string;
	username: string;
	password: string;
	userType: UserType;
};
