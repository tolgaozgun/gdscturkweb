package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.Facilitator;
import com.tolgaozgun.gdscturkweb.model.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FacilitatorMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final UniversityMapper universityMapper;

    public FacilitatorEntity toEntity(Facilitator facilitator) {
        FacilitatorEntity facilitatorEntity = modelMapper.map(facilitator, FacilitatorEntity.class);
        return facilitatorEntity;
    }

    public FacilitatorDTO toDTO(FacilitatorEntity facilitatorEntity) {
        UniversityEntity universityEntity = facilitatorEntity.getUniversity();
        University university = universityMapper.toModel(universityEntity);

        UserEntity userEntity = facilitatorEntity.getUser();
        UserDTO userDTO = userMapper.toDTO(userEntity);

        return new FacilitatorDTO(userDTO, university, facilitatorEntity.getFacilitatorId());
    }


    public List<FacilitatorDTO> toDTO(List<FacilitatorEntity> facilitatorEntityList) {
        return facilitatorEntityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public List<Facilitator> toModel(List<FacilitatorEntity> facilitatorEntityList) {
        return facilitatorEntityList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Facilitator toModel(FacilitatorEntity facilitatorEntity) {
        Facilitator facilitator = modelMapper.map(facilitatorEntity, Facilitator.class);
        return facilitator;
    }
}