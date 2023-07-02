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
    private final UserMapper userMapper;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    public BuddyTeamEntity toEntity(BuddyTeam buddyTeam) {
        BuddyTeamEntity buddyTeamEntity = modelMapper.map(buddyTeam, BuddyTeamEntity.class);

        return buddyTeamEntity;
    }

    public BuddyTeamDTO toDTO(BuddyTeamEntity buddyTeamEntity) {

        BuddyTeamDTO buddyTeam = modelMapper.map(buddyTeamEntity, BuddyTeamDTO.class);

        return buddyTeam;
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