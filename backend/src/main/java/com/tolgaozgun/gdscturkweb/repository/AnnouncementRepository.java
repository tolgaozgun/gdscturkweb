package com.tolgaozgun.gdscturkweb.repository;


import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {

    Optional<AnnouncementEntity> findById(@NonNull Long id);

    List<AnnouncementEntity> findAllByPermittedUserTypesContainingAndStartDateIsBeforeAndEndDateIsAfter(@NonNull UserType userType, @NotNull Date date, @NotNull Date date2);




}
