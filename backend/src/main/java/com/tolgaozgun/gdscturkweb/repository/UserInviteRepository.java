package com.tolgaozgun.gdscturkweb.repository;

import com.tolgaozgun.gdscturkweb.entity.TopicEntity;
import com.tolgaozgun.gdscturkweb.entity.UserInviteEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInviteRepository extends JpaRepository<UserInviteEntity, Long> {

    List<UserInviteEntity> findAllByInvitedBy(@NonNull UserEntity invitedBy);


}