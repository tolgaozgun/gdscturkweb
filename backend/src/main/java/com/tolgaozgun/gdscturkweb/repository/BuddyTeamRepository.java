package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuddyTeamRepository extends JpaRepository<BuddyTeamEntity, Long> {

    Optional<BuddyTeamEntity> findById(@NonNull Long id);

    Optional<BuddyTeamEntity> findByLeadsContains(@NonNull UserEntity userEntity);

    Optional<BuddyTeamEntity> findByFacilitator(@NonNull UserEntity userEntity);

}