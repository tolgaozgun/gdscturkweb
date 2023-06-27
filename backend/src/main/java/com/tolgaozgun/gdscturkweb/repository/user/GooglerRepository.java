package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.GooglerEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("googlerRepository")
@Repository
public interface GooglerRepository extends JpaRepository<GooglerEntity, Long> {
    Optional<GooglerEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

}