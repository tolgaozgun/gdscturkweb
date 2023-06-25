package com.tolgaozgun.gdscturkweb.model.user;


import com.tolgaozgun.gdscturkweb.model.University;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeamMember {

    @Id
    private Long coreTeamMemberId;

    @NotNull
    private University university;

    @NotNull
    private User user;

}
