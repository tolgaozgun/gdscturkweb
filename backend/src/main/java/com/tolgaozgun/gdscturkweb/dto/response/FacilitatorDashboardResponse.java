package com.tolgaozgun.gdscturkweb.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitatorDashboardResponse {

    @JsonProperty("facilitator")
    private FacilitatorDTO facilitatorDTO;

    private Integer buddyTeamSize;

    private Map<Date, Boolean> buddyMeetings;

    @Nullable
    private Date promotedAt;

}
