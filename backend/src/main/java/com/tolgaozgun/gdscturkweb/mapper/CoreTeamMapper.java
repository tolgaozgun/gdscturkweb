package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CoreTeamMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    public CoreTeamMemberEntity toEntity(CoreTeamMember coreTeamMember) {
        CoreTeamMemberEntity coreTeamMemberEntity = modelMapper.map(coreTeamMember, CoreTeamMemberEntity.class);

        return coreTeamMemberEntity;
    }


    public CoreTeamMember toModel(CoreTeamMemberEntity coreTeamMemberEntity) {

        CoreTeamMember coreTeamMember = modelMapper.map(coreTeamMemberEntity, CoreTeamMember.class);

        return coreTeamMember;
    }
}