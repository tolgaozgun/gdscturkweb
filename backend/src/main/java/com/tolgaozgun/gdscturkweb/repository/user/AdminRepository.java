package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.AdminEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("adminRepository")
@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findById(@NonNull Long leadId);

    boolean existsById(@NonNull Long leadId);

}