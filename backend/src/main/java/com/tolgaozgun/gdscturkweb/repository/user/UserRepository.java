package com.tolgaozgun.gdscturkweb.repository.user;

import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.user.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("userRepository")
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(@NonNull String username);
    Optional<UserEntity> findByEmail(@NonNull String email);
    boolean existsByUsername(@NonNull String username);
    boolean existsByEmail(@NonNull String email);

    List<UserEntity> findAllByIsVerifiedAndIsBlackListed(@NonNull Boolean isVerified, @NonNull Boolean isBlackListed);

    Optional<UserEntity> findByUsernameAndIsVerified(@NonNull String username, @NonNull Boolean isVerified);
    Optional<UserEntity> findByEmailAndIsVerified(@NonNull String email, @NonNull Boolean isVerified);
    boolean existsByUsernameAndIsVerified(@NonNull String username, @NonNull Boolean isVerified);
    boolean existsByEmailAndIsVerified(@NonNull String email, @NonNull Boolean isVerified);

}