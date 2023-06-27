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
public class CoreTeamMemberDTO {

    @NotNull
    private Long coreTeamMemberId;

    @NotNull
    private University university;

    @NotNull
    private UserDTO userDTO;

    public CoreTeamMemberDTO(UserDTO userDTO, University university, Long coreTeamMemberId) {
        this.userDTO = userDTO;
        this.university = university;
        this.coreTeamMemberId = coreTeamMemberId;
    }

}
