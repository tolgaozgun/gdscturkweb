import { FacilitatorModel, LeadModel } from ".";


export type BuddyTeam = {
	buddyTeamId: number;
	facilitator: FacilitatorModel;
	leads: Array<LeadModel>;
}

export type EditBuddyTeam = {
	facilitator?: number;
	leads?: Array<number>;
}
