package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.AttendanceDTO;
import com.tolgaozgun.gdscturkweb.dto.request.attendance.CreateAttendanceRequest;
import com.tolgaozgun.gdscturkweb.dto.request.attendance.EditAttendanceRequest;
import com.tolgaozgun.gdscturkweb.entity.AttendanceEntity;
import com.tolgaozgun.gdscturkweb.entity.AttendanceStatusEntity;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.*;
import com.tolgaozgun.gdscturkweb.mapper.AttendanceMapper;
import com.tolgaozgun.gdscturkweb.model.LeadAttendance;
import com.tolgaozgun.gdscturkweb.repository.AttendanceRepository;
import com.tolgaozgun.gdscturkweb.repository.AttendanceStatusRepository;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.user.FacilitatorRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final LeadRepository leadRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final BuddyTeamRepository buddyTeamRepository;
    private final AttendanceStatusRepository attendanceStatusRepository;
    private final AttendanceRepository attendanceRepository;

    private final AttendanceMapper attendanceMapper;

    private final AuthService authService;

    public List<LeadAttendance> getAllAttendancesOfCurrentUser(){
        try {

            UserEntity userEntity = authService.getCurrentUserEntity();

            Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(userEntity);

            if (optionalLeadEntity.isEmpty()) {
                    throw new LeadNotFoundException();
            }

            LeadEntity leadEntity = optionalLeadEntity.get();

            List<AttendanceStatusEntity> attendanceStatusEntities = attendanceStatusRepository.findAllByLead(leadEntity);

            List<AttendanceEntity> attendanceEntities = attendanceRepository.findAllByAttendanceStatusIn(attendanceStatusEntities);

            return attendanceMapper.toLeadAttendance(attendanceEntities, leadEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<AttendanceDTO> getAllAttendancesOfFacilitator(Long facilitatorId) {
        try {
            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findById(facilitatorId);

            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }

            FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

            Optional<BuddyTeamEntity> optionalBuddyTeamEntity = buddyTeamRepository.findByFacilitator(facilitatorEntity);

            if (optionalBuddyTeamEntity.isEmpty()) {
                throw new BuddyTeamNotFoundException();
            }

            BuddyTeamEntity buddyTeamEntity = optionalBuddyTeamEntity.get();

            List<AttendanceEntity> attendanceEntities = attendanceRepository.findAllByBuddyTeam(buddyTeamEntity);

            return attendanceMapper.toDTO(attendanceEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<LeadAttendance> getAllAttendancesOfLead(Long leadId){
        try {

            Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(leadId);

            if (optionalLeadEntity.isEmpty()) {
                throw new LeadNotFoundException();
            }

            LeadEntity leadEntity = optionalLeadEntity.get();

            return getAttendancesByLeadEntity(leadEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<LeadAttendance> getAttendancesByLeadEntity(LeadEntity leadEntity) {
        try {
            List<AttendanceStatusEntity> attendanceStatusEntities = attendanceStatusRepository.findAllByLead(leadEntity);

            List<AttendanceEntity> attendanceEntities = attendanceRepository.findAllByAttendanceStatusIn(attendanceStatusEntities);

            return attendanceMapper.toLeadAttendance(attendanceEntities, leadEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<AttendanceDTO> getAllAttendances(){
        try{
            List<AttendanceEntity> attendanceEntities = attendanceRepository.findAll();

            return attendanceMapper.toDTO(attendanceEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public AttendanceDTO getAttendanceById(Long attendanceId) {
        try {
            Optional<AttendanceEntity> optionalAttendanceEntity = attendanceRepository.findById(attendanceId);

            if (optionalAttendanceEntity.isEmpty()) {
                throw new AttendanceNotFoundException(attendanceId);
            }

            AttendanceEntity announcementEntity = optionalAttendanceEntity.get();
            return attendanceMapper.toDTO(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public AttendanceDTO createAttendance(CreateAttendanceRequest createAttendanceRequest){
        try {
            AttendanceEntity attendanceEntity = new AttendanceEntity();

            Long buddyTeamId = createAttendanceRequest.getBuddyTeamId();
            Date attendanceDate = createAttendanceRequest.getAttendanceDate();
            Map<Long, Boolean> attendanceMap = createAttendanceRequest.getAttendanceStatusMap();

            Optional<BuddyTeamEntity> optionalBuddyTeam = buddyTeamRepository.findById(buddyTeamId);

            if (optionalBuddyTeam.isEmpty()) {
                throw new BuddyTeamNotFoundException();
            }

            BuddyTeamEntity buddyTeamEntity = optionalBuddyTeam.get();
            attendanceEntity.setBuddyTeam(buddyTeamEntity);

            List<AttendanceStatusEntity> attendanceStatusEntities = new ArrayList<>();

            for(Map.Entry<Long, Boolean> entry : attendanceMap.entrySet()){
                Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(entry.getKey());
                if(optionalLeadEntity.isEmpty()){
                    throw new LeadNotFoundException();
                }
                LeadEntity leadEntity = optionalLeadEntity.get();

                AttendanceStatusEntity attendanceStatusEntity = new AttendanceStatusEntity();
                attendanceStatusEntity.setLead(leadEntity);
                attendanceStatusEntity.setAttendanceStatus(entry.getValue());
                attendanceStatusRepository.save(attendanceStatusEntity);
                attendanceStatusEntities.add(attendanceStatusEntity);
            }

            attendanceEntity.setAttendanceDate(attendanceDate);

            attendanceEntity = attendanceRepository.save(attendanceEntity);
            Date currentDate = new Date();
            attendanceEntity.setCreatedDate(currentDate);
            attendanceEntity.setLastEditedDate(currentDate);
            attendanceEntity.setAttendanceStatus(attendanceStatusEntities);

            return attendanceMapper.toDTO(attendanceEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public AttendanceDTO editAttendance(Long attendanceId, EditAttendanceRequest editAttendanceRequest){
        try {

            Optional<AttendanceEntity> optionalAttendanceEntity = attendanceRepository.findById(attendanceId);

            if (optionalAttendanceEntity.isEmpty()) {
                throw new AttendanceNotFoundException(attendanceId);
            }

            AttendanceEntity attendanceEntity = optionalAttendanceEntity.get();

            attendanceEntity.setLastEditedDate(new Date());

            if (editAttendanceRequest.getAttendanceDate() != null) {
                attendanceEntity.setAttendanceDate(editAttendanceRequest.getAttendanceDate());
            }

            if (editAttendanceRequest.getAttendanceStatusMap() != null) {
                List<AttendanceStatusEntity> attendanceStatusEntities = new ArrayList<>();

                for(Map.Entry<Long, Boolean> entry : editAttendanceRequest.getAttendanceStatusMap().entrySet()){
                    Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(entry.getKey());
                    if(optionalLeadEntity.isEmpty()){
                        throw new LeadNotFoundException();
                    }
                    LeadEntity leadEntity = optionalLeadEntity.get();

                    AttendanceStatusEntity attendanceStatusEntity = new AttendanceStatusEntity();
                    attendanceStatusEntity.setLead(leadEntity);
                    attendanceStatusEntity.setAttendanceStatus(entry.getValue());
                    attendanceStatusRepository.save(attendanceStatusEntity);
                    attendanceStatusEntities.add(attendanceStatusEntity);
                }
                attendanceEntity.setAttendanceStatus(attendanceStatusEntities);
            }

            if (editAttendanceRequest.getBuddyTeamId() != null) {
                Long buddyTeamId = editAttendanceRequest.getBuddyTeamId();

                Optional<BuddyTeamEntity> optionalBuddyTeamEntity = buddyTeamRepository.findById(buddyTeamId);

                if(optionalBuddyTeamEntity.isEmpty()){
                    throw new BuddyTeamNotFoundException();
                }

                BuddyTeamEntity buddyTeamEntity = optionalBuddyTeamEntity.get();

                attendanceEntity.setBuddyTeam(buddyTeamEntity);
            }

            attendanceEntity = attendanceRepository.save(attendanceEntity);
            return attendanceMapper.toDTO(attendanceEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
