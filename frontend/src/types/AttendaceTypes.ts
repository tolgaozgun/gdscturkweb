import { LeadModel } from ".";
import { BuddyTeam } from "./BuddyTeamTypes";

export type Attendance = {
    attendanceId: number;
    buddyTeam: BuddyTeam;
    attendanceDate: Date;
    createdDate: Date;
    lastEditedDate: Date;
    attendanceStatusMap: Map<LeadModel, boolean>;
}

export type AttendanceStatus = {
    lead: LeadModel;
    attendanceStatus: boolean;
}

export type LeadAttendance = {
    attendanceId: number;
    buddyTeam: BuddyTeam;
    attendanceDate: Date;
    createdDate: Date;
    lastEditedDate: Date;
    attendanceStatus: boolean;
}

export type CreateAttendance = {
    buddyTeamId: number;
    attendanceDate: Date;
    attendanceStatusMap: Map<number, boolean>;
}

export type EditAttendance = {
    buddyTeamId?: number;
    attendanceDate?: Date;
    attendanceStatusMap?: Map<number, boolean>;
}

