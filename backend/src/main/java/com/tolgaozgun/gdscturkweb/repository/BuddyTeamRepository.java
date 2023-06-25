package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuddyTeamRepository extends JpaRepository<BuddyTeamEntity, Long> {

    Optional<BuddyTeamEntity> findById(@NonNull Long id);



}