package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.CoreTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.CountryEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.model.CoreTeam;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoreTeamRepository extends JpaRepository<CoreTeamEntity, Long> {

    Optional<CoreTeamEntity> findCoreTeamEntityByUniversity(@NonNull UniversityEntity universityEntity);


    Optional<CoreTeamEntity> findCoreTeamEntityByLead(@NonNull LeadEntity leadEntity);


}