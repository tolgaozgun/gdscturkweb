package com.tolgaozgun.gdscturkweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadDTO {

    @NotNull
    private Long leadId;

    @NotNull
    private University university;

//    @NotNull
//    private BuddyTeam buddyTeam;

    @NotNull
    @JsonProperty("user")
    private UserDTO userDTO;

    public LeadDTO(UserDTO userDTO, University university, Long leadId) {
        this.userDTO = userDTO;
        this.university = university;
//        this.buddyTeam = buddyTeam;
        this.leadId = leadId;
    }

}
