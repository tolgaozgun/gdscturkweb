package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.EmailVerificationEntity;
import com.tolgaozgun.gdscturkweb.entity.UserInviteEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInvitationRepository extends JpaRepository<UserInviteEntity, Long> {

    Optional<UserInviteEntity> findByEmailAndInviteCode(@NonNull String email, @NonNull String inviteCode);

}
