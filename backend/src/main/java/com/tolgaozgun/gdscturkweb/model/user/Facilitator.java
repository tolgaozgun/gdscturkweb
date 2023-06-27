package com.tolgaozgun.gdscturkweb.model.user;

import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facilitator {

    @Id
    private Long facilitatorId;

    @NotNull
    private University university;

    @NotNull
    private User user;



}