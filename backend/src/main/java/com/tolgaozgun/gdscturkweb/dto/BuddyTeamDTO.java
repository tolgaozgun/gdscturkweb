package com.tolgaozgun.gdscturkweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuddyTeamDTO {
    private Long buddyTeamId;

    @JsonProperty("facilitator")
    private FacilitatorDTO facilitatorDTO;

    private List<LeadDTO> leads;

}