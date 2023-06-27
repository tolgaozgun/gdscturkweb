package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("leadRepository")
@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, Long> {
    Optional<LeadEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

    Optional<LeadEntity> findByUser(@NonNull UserEntity user);

}