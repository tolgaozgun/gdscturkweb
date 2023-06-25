package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("facilitatorRepository")
@Repository
public interface FacilitatorRepository extends JpaRepository<FacilitatorEntity, Long> {
    Optional<FacilitatorEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

}