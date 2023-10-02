package com.tolgaozgun.gdscturkweb.mapper;

import com.tolgaozgun.gdscturkweb.dto.AttendanceDTO;
import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.entity.AttendanceEntity;
import com.tolgaozgun.gdscturkweb.entity.AttendanceStatusEntity;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.model.Attendance;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.model.LeadAttendance;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AttendanceMapper {

    private final ModelMapper modelMapper;
    private final BuddyTeamMapper buddyTeamMapper;
    private final LeadMapper leadMapper;

    public AttendanceEntity toEntity(Attendance attendance) {
        return modelMapper.map(attendance, AttendanceEntity.class);
    }

    public Attendance toModel(AttendanceEntity attendanceEntity) {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(attendanceEntity.getAttendanceId());
        attendance.setAttendanceDate(attendanceEntity.getAttendanceDate());
        attendance.setCreatedDate(attendanceEntity.getCreatedDate());
        attendance.setLastEditedDate(attendanceEntity.getLastEditedDate());
        BuddyTeam buddyTeam = buddyTeamMapper.toModel(attendanceEntity.getBuddyTeam());
        attendance.setBuddyTeam(buddyTeam);
        Map<Lead, Boolean> attendanceStatusMap = new HashMap<>();
        for(AttendanceStatusEntity attendanceStatusEntity : attendanceEntity.getAttendanceStatus()) {
            LeadEntity leadEntity = attendanceStatusEntity.getLead();
            Boolean attendanceStatus = attendanceStatusEntity.getAttendanceStatus();
            attendanceStatusMap.put(leadMapper.toModel(leadEntity), attendanceStatus);
        }
        attendance.setAttendanceStatusMap(attendanceStatusMap);
        return attendance;
    }

    public List<Attendance> toModel(List<AttendanceEntity> attendanceEntities) {
        return attendanceEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public AttendanceDTO toDTO(AttendanceEntity attendanceEntity) {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setAttendanceId(attendanceEntity.getAttendanceId());
        attendanceDTO.setAttendanceDate(attendanceEntity.getAttendanceDate());
        attendanceDTO.setCreatedDate(attendanceEntity.getCreatedDate());
        attendanceDTO.setLastEditedDate(attendanceEntity.getLastEditedDate());
        BuddyTeamDTO buddyTeamDTO = buddyTeamMapper.toDTO(attendanceEntity.getBuddyTeam());
        attendanceDTO.setBuddyTeam(buddyTeamDTO);
        Map<LeadDTO, Boolean> attendanceStatusMap = new HashMap<>();
        for(AttendanceStatusEntity attendanceStatusEntity : attendanceEntity.getAttendanceStatus()) {
            LeadEntity leadEntity = attendanceStatusEntity.getLead();
            Boolean attendanceStatus = attendanceStatusEntity.getAttendanceStatus();
            attendanceStatusMap.put(leadMapper.toDTO(leadEntity), attendanceStatus);
        }
        attendanceDTO.setAttendanceStatusMap(attendanceStatusMap);
        return attendanceDTO;
    }

    public List<AttendanceDTO> toDTO(List<AttendanceEntity> attendanceEntities) {
        return attendanceEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<LeadAttendance> toLeadAttendance(List<AttendanceEntity> attendanceEntities, LeadEntity leadEntity) {
        return attendanceEntities.stream()
                .map(attendanceEntity -> toLeadAttendance(attendanceEntity, leadEntity))
                .collect(Collectors.toList());
    }

    public LeadAttendance toLeadAttendance(AttendanceEntity attendanceEntity, LeadEntity leadEntity) {

        LeadAttendance leadAttendance = new LeadAttendance();
        leadAttendance.setAttendanceId(attendanceEntity.getAttendanceId());
        leadAttendance.setAttendanceDate(attendanceEntity.getAttendanceDate());
        leadAttendance.setCreatedDate(attendanceEntity.getCreatedDate());
        leadAttendance.setLastEditedDate(attendanceEntity.getLastEditedDate());

        BuddyTeamEntity buddyTeamEntity = attendanceEntity.getBuddyTeam();
        BuddyTeam buddyTeam = buddyTeamMapper.toModel(buddyTeamEntity);

        leadAttendance.setBuddyTeam(buddyTeam);


        List<AttendanceStatusEntity> attendanceStatusEntities = attendanceEntity.getAttendanceStatus();

        Optional<Boolean> attendanceStatusOptional = attendanceStatusEntities.stream()
                .filter(entity -> entity.getLead().equals(leadEntity))
                .map(AttendanceStatusEntity::getAttendanceStatus)
                .findFirst();

        leadAttendance.setAttendanceStatus(attendanceStatusOptional.orElse(false));


        return leadAttendance;
    }


}