package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.AttendanceDTO;
import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.register.FacilitatorRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.FacilitatorDashboardResponse;
import com.tolgaozgun.gdscturkweb.dto.response.LeadDashboardResponse;
import com.tolgaozgun.gdscturkweb.dto.user.register.FacilitatorRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.BuddyTeamNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.FacilitatorNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.LeadNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.FacilitatorMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.model.LeadAttendance;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.FacilitatorRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FacilitatorService {

    private final AuthService authService;

    private final UniversityRepository universityRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    private final FacilitatorMapper facilitatorMapper;

    private final AttendanceService attendanceService;

    public List<FacilitatorDTO> getAllFacilitators() {
        try {
            List<FacilitatorEntity> facilitatorEntities = facilitatorRepository.findAll();
            return facilitatorMapper.toDTO(facilitatorEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }



    protected FacilitatorEntity getFacilitatorEntity(UserEntity userEntity) {
        try {
            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(userEntity);
            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }
            return optionalFacilitatorEntity.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public FacilitatorDTO registerFacilitator(FacilitatorRegisterRequest facilitatorRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = facilitatorRegisterRequest.getUserRegister();
            FacilitatorRegister facilitatorRegister = facilitatorRegisterRequest.getFacilitatorRegister();

            UserEntity savedEntity = authService.checkAndRegisterUser(userRegister, UserType.FACILITATOR);


            UniversityEntity universityEntity = universityRepository.findById(facilitatorRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            FacilitatorEntity facilitatorEntity = new FacilitatorEntity();
            facilitatorEntity.setUniversity(universityEntity);
            facilitatorEntity.setUser(savedEntity);
            facilitatorEntity = facilitatorRepository.save(facilitatorEntity);

            BuddyTeamEntity buddyTeamEntity = new BuddyTeamEntity();
            buddyTeamEntity.setFacilitator(facilitatorEntity);
            buddyTeamEntity.setLeads(List.of());
            buddyTeamEntity.setName(facilitatorEntity.getUser().getName() + "'s Buddy Team");

            buddyTeamRepository.save(buddyTeamEntity);

            return facilitatorMapper.toDTO(facilitatorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public FacilitatorDashboardResponse getCurrentFacilitatorDashboard(){
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            FacilitatorEntity facilitatorEntity = getFacilitatorEntity(userEntity);

            Optional<BuddyTeamEntity> buddyTeamEntity = buddyTeamRepository.findByFacilitator(facilitatorEntity);

            Integer buddyTeamSize = null;
            if (buddyTeamEntity.isPresent()) {
                buddyTeamSize = buddyTeamEntity.get().getLeads().size();
            }

            FacilitatorDashboardResponse facilitatorDashboardResponse = new FacilitatorDashboardResponse();
            facilitatorDashboardResponse.setFacilitatorDTO(facilitatorMapper.toDTO(facilitatorEntity));
            facilitatorDashboardResponse.setBuddyTeamSize(buddyTeamSize);

            List<AttendanceDTO> attendances = attendanceService.getAllAttendancesByFacilitatorEntity(facilitatorEntity);

            // Create Map<Date,Boolean> using leadAttendances.getAttendanceDate() and leadAttendances.getAttendanceStatus()
            Map<Date, Boolean> buddyMeetings = new HashMap<>();
            for (AttendanceDTO attendance : attendances) {
                buddyMeetings.put(attendance.getAttendanceDate(), true);
            }
            facilitatorDashboardResponse.setBuddyMeetings(buddyMeetings);

            facilitatorDashboardResponse.setPromotedAt(userEntity.getPromotedAt());

            return facilitatorDashboardResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
