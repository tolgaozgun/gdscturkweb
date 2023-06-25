package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {

    Optional<UniversityEntity> findById(@NonNull Long id);


}