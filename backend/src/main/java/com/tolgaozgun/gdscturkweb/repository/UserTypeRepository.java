package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.PermissionEntity;
import com.tolgaozgun.gdscturkweb.entity.UserTypeEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {

    Optional<UserTypeEntity> findById(@NonNull Long id);

    List<UserTypeEntity> findAll();

    Optional<UserTypeEntity> findByUserType(@NonNull UserType userType);



}