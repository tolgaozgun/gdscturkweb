package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.AttachmentEntity;
import com.tolgaozgun.gdscturkweb.entity.PermissionEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findById(@NonNull Long id);

    List<PermissionEntity> findAll();




}