package com.tolgaozgun.gdscturkweb.dto;

import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreTeamDTO {

    private Long coreTeamId;

    private String name;

    private LeadDTO lead;

    private List<CoreTeamMemberDTO> coreTeamMembers;

    private University university;
}
