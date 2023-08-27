package com.tolgaozgun.gdscturkweb.repository;


import com.tolgaozgun.gdscturkweb.entity.AttendanceEntity;
import com.tolgaozgun.gdscturkweb.entity.AttendanceStatusEntity;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceStatusRepository extends JpaRepository<AttendanceStatusEntity, Long> {

    Optional<AttendanceStatusEntity> findById(@NonNull Long id);

    List<AttendanceStatusEntity> findAllByLead(@NonNull LeadEntity leadEntity);

    List<AttendanceStatusEntity> findAllByLeadAndAttendanceStatus(@NonNull LeadEntity leadEntity, @NonNull Boolean attendanceStatus);


}
