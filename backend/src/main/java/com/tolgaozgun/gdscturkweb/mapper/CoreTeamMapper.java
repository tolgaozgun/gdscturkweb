package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoreTeamMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final UniversityMapper universityMapper;

    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    public CoreTeamMemberEntity toEntity(CoreTeamMember coreTeamMember) {
        CoreTeamMemberEntity coreTeamMemberEntity = modelMapper.map(coreTeamMember, CoreTeamMemberEntity.class);

        return coreTeamMemberEntity;
    }

    public CoreTeamMemberDTO toDTO(CoreTeamMemberEntity coreTeamMemberEntity) {
        UserEntity userEntity = coreTeamMemberEntity.getUser();
        UserDTO userDTO = userMapper.toDTO(userEntity);

        UniversityEntity universityEntity = coreTeamMemberEntity.getUniversity();
        University university = universityMapper.toModel(universityEntity);

        Long id = coreTeamMemberEntity.getCoreTeamMemberId();

        return new CoreTeamMemberDTO(userDTO, university, id);
    }


    public List<CoreTeamMemberDTO> toDTO(List<CoreTeamMemberEntity> coreTeamMemberEntities) {
        return coreTeamMemberEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CoreTeamMember toModel(CoreTeamMemberEntity coreTeamMemberEntity) {

        CoreTeamMember coreTeamMember = modelMapper.map(coreTeamMemberEntity, CoreTeamMember.class);

        return coreTeamMember;
    }
}