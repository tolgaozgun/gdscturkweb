package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.request.permission.GetPermissionsByUserIdRequest;
import com.tolgaozgun.gdscturkweb.entity.PermissionEntity;
import com.tolgaozgun.gdscturkweb.entity.UserTypeEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserTypeNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.PermissionMapper;
import com.tolgaozgun.gdscturkweb.model.Permission;
import com.tolgaozgun.gdscturkweb.repository.UserTypeRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PermissionService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    private final PermissionMapper permissionMapper;

    public List<Permission> getCurrentUserPermissions(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            UserType userType = userEntity.getUserType();

            Optional<UserTypeEntity> optionalUserTypeEntity = userTypeRepository.findByUserType(userType);

            if (optionalUserTypeEntity.isEmpty()) {
                throw new UserTypeNotFoundException();
            }

            UserTypeEntity userTypeEntity = optionalUserTypeEntity.get();

            List<PermissionEntity> permissionEntities = userTypeEntity.getPermissions();
            List<PermissionEntity> additionalPermissions = userEntity.getAdditionalPermissions();

            permissionEntities.addAll(additionalPermissions);

            return permissionMapper.toModel(permissionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<Permission> getUserPermissions(GetPermissionsByUserIdRequest getPermissionsByUserIdRequest){
        try {
            Long userId = getPermissionsByUserIdRequest.getUserId();

            Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            UserType userType = userEntity.getUserType();

            Optional<UserTypeEntity> optionalUserTypeEntity = userTypeRepository.findByUserType(userType);

            if (optionalUserTypeEntity.isEmpty()) {
                throw new UserTypeNotFoundException();
            }

            UserTypeEntity userTypeEntity = optionalUserTypeEntity.get();

            List<PermissionEntity> permissionEntities = userTypeEntity.getPermissions();
            List<PermissionEntity> additionalPermissions = userEntity.getAdditionalPermissions();

            permissionEntities.addAll(additionalPermissions);

            return permissionMapper.toModel(permissionEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
