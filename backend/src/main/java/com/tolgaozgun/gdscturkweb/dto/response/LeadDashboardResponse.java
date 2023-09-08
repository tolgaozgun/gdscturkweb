package com.tolgaozgun.gdscturkweb.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LeadDashboardResponse {

    @JsonProperty("lead")
    private LeadDTO leadDTO;

    private Date lastLogin;

    private Integer buddyTeamSize;

    private Integer coreTeamSize;

    private List<Date> activitiesByMonth;

    private Map<Date, Boolean> buddyMeetings;

    private Integer buddyTeamMeetings;

}
