package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("coreTeamMemberRepository")
@Repository
public interface CoreTeamMemberRepository extends JpaRepository<CoreTeamMemberEntity, Long> {
    Optional<CoreTeamMemberEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

}