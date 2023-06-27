package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.UserDTO;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.user.User;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    public UserEntity toEntity(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        return userEntity;
    }

    public User toModel(UserEntity userEntity) {
        User user = modelMapper.map(userEntity, User.class);
        return user;
    }

    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;

    }
}