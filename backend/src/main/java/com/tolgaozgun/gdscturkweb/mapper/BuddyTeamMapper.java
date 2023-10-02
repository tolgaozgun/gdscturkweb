package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BuddyTeamMapper {

    private final ModelMapper modelMapper;
    private final FacilitatorMapper facilitatorMapper;
    private final LeadMapper leadMapper;

    public BuddyTeamEntity toEntity(BuddyTeam buddyTeam) {
        BuddyTeamEntity buddyTeamEntity = modelMapper.map(buddyTeam, BuddyTeamEntity.class);

        return buddyTeamEntity;
    }

    public BuddyTeamDTO toDTO(BuddyTeamEntity buddyTeamEntity) {

        BuddyTeamDTO buddyTeamDTO = new BuddyTeamDTO();
        buddyTeamDTO.setBuddyTeamId(buddyTeamEntity.getId());
        buddyTeamDTO.setFacilitatorDTO(facilitatorMapper.toDTO(buddyTeamEntity.getFacilitator()));
        buddyTeamDTO.setLeads(leadMapper.toDTO(buddyTeamEntity.getLeads()));

        return buddyTeamDTO;
    }

    public List<BuddyTeamDTO> toDTO(List<BuddyTeamEntity> buddyTeamEntityList) {
        return buddyTeamEntityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public BuddyTeam toModel(BuddyTeamEntity buddyTeamEntity) {

        BuddyTeam buddyTeam = modelMapper.map(buddyTeamEntity, BuddyTeam.class);

        return buddyTeam;
    }
}