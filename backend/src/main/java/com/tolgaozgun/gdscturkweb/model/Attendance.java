package com.tolgaozgun.gdscturkweb.model;

import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

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
    private Map<Lead, Boolean> attendanceStatusMap = new HashMap<>();
}
