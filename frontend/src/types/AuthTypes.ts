export type Tokens = {
	accessToken: string | null;
	refreshToken: string | null;
};

export type UserRegisterModel = {
	name: string;
	surname: string;
	username: string;
	email: string;
	password: string;
};

export type LeadRegisterModel = {
	universityId: number;
}

export type GooglerRegisterModel = {

}

export type FacilitatorRegisterModel = {
	universityId: number;
}

export type CoreTeamRegisterModel = {
	universityId: number;
}

export type AdminRegisterModel = {

}

export type RegisterLead = {
	user: UserRegisterModel;
	lead: LeadRegisterModel;
}

export type RegisterGoogler = {
	user: UserRegisterModel;
	googler: GooglerRegisterModel;
}

export type RegisterFacilitator = {
	user: UserRegisterModel;
	facilitator: FacilitatorRegisterModel;
}

export type RegisterCoreTeam = {
	user: UserRegisterModel;
	coreTeam: CoreTeamRegisterModel;
}

export type RegisterAdmin = {
	user: UserRegisterModel;
	admin: AdminRegisterModel;
}
