import { CoreTeamMemberModel, LeadModel } from ".";
import { University } from "./UniversityTypes";

export type CoreTeam = {
    coreTeamId: number;
    name: string;
    lead: LeadModel;
    coreTeamMembers: Array<CoreTeamMemberModel>;
    university: University;
}
