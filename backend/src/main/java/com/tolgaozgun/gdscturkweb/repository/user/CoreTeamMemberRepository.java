package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.user.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("coreTeamMemberRepository")
@Repository
public interface CoreTeamMemberRepository extends JpaRepository<CoreTeamMemberEntity, Long> {
    Optional<CoreTeamMemberEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

    Optional<CoreTeamMemberEntity> findByUser(@NonNull UserEntity user);

    List<CoreTeamMemberEntity> findAllByUniversity(@NonNull UniversityEntity university);
}