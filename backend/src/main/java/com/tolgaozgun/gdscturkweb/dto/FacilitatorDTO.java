package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitatorDTO {

    @NotNull
    private Long facilitatorId;

    @NotNull
    private University university;

    @NotNull
    private UserDTO userDTO;

    public FacilitatorDTO(UserDTO userDTO, University university, Long facilitatorId) {
        this.userDTO = userDTO;
        this.university = university;
        this.facilitatorId = facilitatorId;
    }

}
