package com.tolgaozgun.gdscturkweb.repository;


import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.entity.AttendanceEntity;
import com.tolgaozgun.gdscturkweb.entity.AttendanceStatusEntity;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    Optional<AttendanceEntity> findById(@NonNull Long id);
    List<AttendanceEntity> findAllByBuddyTeam(@NonNull BuddyTeamEntity buddyTeamEntity);
    List<AttendanceEntity> findAllByBuddyTeamAndAttendanceDateBetween(@NonNull BuddyTeamEntity buddyTeamEntity, @NonNull Date startDate, @NonNull Date endDate);
    List<AttendanceEntity> findAllByAttendanceStatusIn(@NonNull List<AttendanceStatusEntity> attendanceStatusEntityList);

}
