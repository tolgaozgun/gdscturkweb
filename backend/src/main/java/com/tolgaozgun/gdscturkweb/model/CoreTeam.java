package com.tolgaozgun.gdscturkweb.model;


import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeam {

    private Long coreTeamId;

    private String name;

    private Lead lead;

    private List<CoreTeamMember> coreTeamMembers;

    private University university;
}