package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import com.tolgaozgun.gdscturkweb.model.user.User;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeadMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final UniversityMapper universityMapper;
    private final BuddyTeamMapper buddyTeamMapper;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    public LeadEntity toEntity(Lead lead) {
        LeadEntity leadEntity = modelMapper.map(lead, LeadEntity.class);

        // Fetch UniversityEntity from the repository and set it
        UniversityEntity universityEntity = universityRepository.findById(lead.getUniversity().getUniversityId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid University ID"));
        leadEntity.setUniversity(universityEntity);

        // Fetch BuddyTeamEntity from the repository and set it
        BuddyTeamEntity buddyTeamEntity = buddyTeamRepository.findById(lead.getBuddyTeam().getBuddyTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Buddy Team ID"));
        leadEntity.setBuddyTeam(buddyTeamEntity);

        return leadEntity;
    }


    public LeadDTO toDTO(LeadEntity leadEntity) {

        UserDTO userDTO = userMapper.toDTO(leadEntity.getUser());

        UniversityEntity universityEntity = leadEntity.getUniversity();
        University university = universityMapper.toModel(universityEntity);

        BuddyTeamEntity buddyTeamEntity = leadEntity.getBuddyTeam();
        BuddyTeam buddyTeam = null;
        if (buddyTeamEntity != null) {
            buddyTeam = buddyTeamMapper.toModel(buddyTeamEntity);
        }

        return new LeadDTO(userDTO, university, buddyTeam, leadEntity.getLeadId());
    }

    public Lead toModel(LeadEntity leadEntity) {
        User user = userMapper.toModel(leadEntity.getUser());
        UniversityEntity universityEntity = leadEntity.getUniversity();
        University university = universityMapper.toModel(universityEntity);
        BuddyTeamEntity buddyTeamEntity = leadEntity.getBuddyTeam();
        BuddyTeam buddyTeam = null;
        if (buddyTeamEntity != null) {
            buddyTeam = buddyTeamMapper.toModel(buddyTeamEntity);
        }
        Lead lead = new Lead();
        lead.setLeadId(leadEntity.getLeadId());
        lead.setUser(user);
        lead.setUniversity(university);
        lead.setBuddyTeam(buddyTeam);
        return lead;
    }

    public List<LeadDTO> toDTO(List<LeadEntity> leadEntities) {
        return leadEntities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}