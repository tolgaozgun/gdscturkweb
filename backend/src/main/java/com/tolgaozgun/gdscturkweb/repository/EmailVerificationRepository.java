package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.AttendanceStatusEntity;
import com.tolgaozgun.gdscturkweb.entity.EmailVerificationEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {

    Optional<EmailVerificationEntity> findByUserAndVerificationCode(@NonNull UserEntity userEntity,
                                                          @NonNull String verificationCode);

    Optional<EmailVerificationEntity> findByUser(@NonNull UserEntity userEntity);

}
