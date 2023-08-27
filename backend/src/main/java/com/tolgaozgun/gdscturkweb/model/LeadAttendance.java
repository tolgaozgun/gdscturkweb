package com.tolgaozgun.gdscturkweb.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadAttendance {

    @Id
    private Long attendanceId;

    @NotNull
    private BuddyTeam buddyTeam;

    @NotNull
    private Date attendanceDate;

    @NotNull
    private Date createdDate;

    @NotNull
    private Date lastEditedDate;

    @NotNull
    private Boolean attendanceStatus;


}
