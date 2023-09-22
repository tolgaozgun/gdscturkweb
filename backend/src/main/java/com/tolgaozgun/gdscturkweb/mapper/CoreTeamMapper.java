package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.CoreTeamDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.CoreTeamEntity;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.CoreTeam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoreTeamMapper {

    private final ModelMapper modelMapper;
    private final CoreTeamMemberMapper coreTeamMemberMapper;
    private final LeadMapper leadMapper;

    public CoreTeamEntity toEntity(CoreTeam coreTeam) {
        CoreTeamEntity coreTeamEntity = modelMapper.map(coreTeam, CoreTeamEntity.class);
        return coreTeamEntity;
    }

    public CoreTeamDTO toDTO(CoreTeamEntity coreTeamEntity) {

        CoreTeamDTO buddyTeamDTO = new CoreTeamDTO();
        buddyTeamDTO.setCoreTeamId(coreTeamEntity.getId());
        buddyTeamDTO.setLead(leadMapper.toDTO(coreTeamEntity.getLead()));
        buddyTeamDTO.setCoreTeamMembers(coreTeamMemberMapper.toDTO(coreTeamEntity.getCoreTeamMembers()));

        return buddyTeamDTO;
    }

    public List<CoreTeamDTO> toDTO(List<CoreTeamEntity> buddyTeamEntityList) {
        return buddyTeamEntityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public CoreTeam toModel(CoreTeamEntity buddyTeamEntity) {

        CoreTeam buddyTeam = modelMapper.map(buddyTeamEntity, CoreTeam.class);

        return buddyTeam;
    }
}