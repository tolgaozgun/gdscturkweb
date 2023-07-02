package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.entity.CityEntity;
import com.tolgaozgun.gdscturkweb.entity.PermissionEntity;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionMapper {

    private final ModelMapper modelMapper;

    public PermissionEntity toEntity(Permission permission) {
        PermissionEntity permissionEntity = modelMapper.map(permission, PermissionEntity.class);
        return permissionEntity;
    }

    public List<Permission> toModel(List<PermissionEntity> permissionEntities) {
        return permissionEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Permission toModel(PermissionEntity permissionEntity) {
        Permission permission = modelMapper.map(permissionEntity, Permission.class);
        return permission;
    }
}