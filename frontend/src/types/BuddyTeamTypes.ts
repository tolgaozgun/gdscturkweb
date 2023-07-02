import { FacilitatorModel, LeadModel } from ".";


export type BuddyTeam = {
	buddyTeamId: number;
	facilitator: FacilitatorModel;
	leads: Array<LeadModel>;
}

export type BuddyTeamByFacilitator = {
    facilitatorId: number;
}