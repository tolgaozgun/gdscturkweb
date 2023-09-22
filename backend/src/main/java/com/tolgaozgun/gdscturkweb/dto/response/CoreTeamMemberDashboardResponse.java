package com.tolgaozgun.gdscturkweb.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeamMemberDashboardResponse {

    @JsonProperty("coreTeamMember")
    private CoreTeamMemberDTO coreTeamMemberDTO;

    private Integer coreTeamSize;

    private List<Date> eventDates;
}
