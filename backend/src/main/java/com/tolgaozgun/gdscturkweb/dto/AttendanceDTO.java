package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    @Id
    private Long attendanceId;

    @NotNull
    private BuddyTeamDTO buddyTeam;

    @NotNull
    private Date attendanceDate;

    @NotNull
    private Date createdDate;

    @NotNull
    private Date lastEditedDate;

    @NotNull
    private Map<LeadDTO, Boolean> attendanceStatusMap = new HashMap<>();


}
