package com.tolgaozgun.gdscturkweb.model.user;

import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    @Id
    private Long leadId;

    @NotNull
    private University university;

//    private BuddyTeam buddyTeam;
    @NotNull
    private User user;

    @NotNull
    private Date promotedAt;

}