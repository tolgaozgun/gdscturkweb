package com.tolgaozgun.gdscturkweb.dto.request.attendance;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditAttendanceRequest {

    @Nullable
    private Long buddyTeamId;

    @Nullable
    private Date attendanceDate;

    @Nullable
    private Map<Long, Boolean> attendanceStatusMap;

}
