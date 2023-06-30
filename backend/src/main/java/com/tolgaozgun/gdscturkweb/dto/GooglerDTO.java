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
public class GooglerDTO {

    @NotNull
    private Long googlerId;

    @NotNull
    @JsonProperty("user")
    private UserDTO userDTO;

    public GooglerDTO(UserDTO userDTO, Long googlerId) {
        this.userDTO = userDTO;
        this.googlerId = googlerId;
    }

}
