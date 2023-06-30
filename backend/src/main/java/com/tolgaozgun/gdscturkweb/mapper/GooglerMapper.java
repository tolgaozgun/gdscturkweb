package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.GooglerDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.GooglerEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.Googler;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GooglerMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    public GooglerEntity toEntity(Googler googler) {
        GooglerEntity googlerEntity = modelMapper.map(googler, GooglerEntity.class);
        return googlerEntity;
    }


    public GooglerDTO toDTO(GooglerEntity googlerEntity) {
        UserDTO userDTO = userMapper.toDTO(googlerEntity.getUser());
        return new GooglerDTO(userDTO, googlerEntity.getGooglerId());
    }

    public List<GooglerDTO> toDTO(List<GooglerEntity> googlerEntities) {
        return googlerEntities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}