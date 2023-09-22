package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.request.coreTeam.InviteCoreTeamRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.CoreTeamMemberDashboardResponse;
import com.tolgaozgun.gdscturkweb.dto.response.LeadDashboardResponse;
import com.tolgaozgun.gdscturkweb.dto.user.register.CoreTeamRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.CoreTeamMemberNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.LeadNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UniversityNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CoreTeamMemberMapper;
import com.tolgaozgun.gdscturkweb.model.LeadAttendance;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.CoreTeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CoreTeamMemberService {

    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UniversityRepository universityRepository;
    private final CoreTeamMemberMapper coreTeamMemberMapper;

    private final AuthService authService;


    public List<CoreTeamMemberDTO> getAllCoreTeamMembers() {
        try {
            List<CoreTeamMemberEntity> coreTeamMemberEntities = coreTeamMemberRepository.findAll();
            return coreTeamMemberMapper.toDTO(coreTeamMemberEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    protected CoreTeamMemberEntity getCoreTeamMemberEntity(UserEntity userEntity) throws Exception {
        try {
            Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntity = coreTeamMemberRepository.findByUser(userEntity);

            if (optionalCoreTeamMemberEntity.isEmpty()) {
                throw new CoreTeamMemberNotFoundException();
            }

            return optionalCoreTeamMemberEntity.get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<CoreTeamMemberDTO> getAllCoreTeamMembersByUniversity(Long universityId) {
        try {
            Optional<UniversityEntity> optionalUniversityEntity = universityRepository.findById(universityId);

            if (optionalUniversityEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            UniversityEntity universityEntity = optionalUniversityEntity.get();

            List<CoreTeamMemberEntity> coreTeamMemberEntities = coreTeamMemberRepository.findAllByUniversity(universityEntity);
            return coreTeamMemberMapper.toDTO(coreTeamMemberEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public CoreTeamMemberDTO registerCoreTeam(CoreTeamRegisterRequest coreTeamRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = coreTeamRegisterRequest.getUserRegister();
            CoreTeamRegister coreTeamRegister = coreTeamRegisterRequest.getCoreTeamRegister();

            UserEntity savedEntity = authService.checkAndRegisterUser(userRegister, UserType.CORE_TEAM_MEMBER);

            UniversityEntity universityEntity = universityRepository.findById(coreTeamRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            CoreTeamMemberEntity coreTeamMemberEntity = new CoreTeamMemberEntity(universityEntity, savedEntity);


            CoreTeamMemberEntity savedCoreTeamMemberEntity = coreTeamMemberRepository.save(coreTeamMemberEntity);

            return coreTeamMemberMapper.toDTO(savedCoreTeamMemberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CoreTeamMemberDashboardResponse getCurrentDashboard(){
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntityEntity = coreTeamMemberRepository.findByUser(userEntity);

            if (optionalCoreTeamMemberEntityEntity.isEmpty()) {
                throw new CoreTeamMemberNotFoundException();
            }

            CoreTeamMemberEntity coreTeamMemberEntity = optionalCoreTeamMemberEntityEntity.get();

            UniversityEntity universityEntity = coreTeamMemberEntity.getUniversity();

            List<CoreTeamMemberEntity> coreTeamMemberEntities = coreTeamMemberRepository.findAllByUniversity(universityEntity);
            int coreTeamSize = coreTeamMemberEntities.size();

            CoreTeamMemberDashboardResponse coreTeamMemberDashboardResponse = new CoreTeamMemberDashboardResponse();
            coreTeamMemberDashboardResponse.setCoreTeamMemberDTO(coreTeamMemberMapper.toDTO(coreTeamMemberEntity));
            coreTeamMemberDashboardResponse.setCoreTeamSize(coreTeamSize);

            // TODO: Add events

            return coreTeamMemberDashboardResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }




}
