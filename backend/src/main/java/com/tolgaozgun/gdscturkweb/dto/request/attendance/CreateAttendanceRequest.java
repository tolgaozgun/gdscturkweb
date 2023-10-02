package com.tolgaozgun.gdscturkweb.dto.request.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttendanceRequest {

    @NotNull
    private Long buddyTeamId;

    @NotNull
    private Date attendanceDate;

    @NotNull
    private Map<Long, Boolean> attendanceStatusMap;

}
