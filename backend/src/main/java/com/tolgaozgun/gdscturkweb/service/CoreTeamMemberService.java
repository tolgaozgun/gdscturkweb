package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.request.coreTeam.InviteCoreTeamRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.user.register.CoreTeamRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.UniversityNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CoreTeamMapper;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.CoreTeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoreTeamMemberService {

    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UniversityRepository universityRepository;
    private final CoreTeamMapper coreTeamMapper;

    private final AuthService authService;
    private final LeadService leadService;


    public List<CoreTeamMemberDTO> getAllCoreTeamMembers() {
        try {
            List<CoreTeamMemberEntity> coreTeamMemberEntities = coreTeamMemberRepository.findAll();
            return coreTeamMapper.toDTO(coreTeamMemberEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void inviteCoreTeamMember(InviteCoreTeamRequest inviteCoreTeamRequest) throws Exception {
        try {

            UserEntity userEntity = authService.getCurrentUserEntity();

            if (userEntity.getUserType() != UserType.LEAD) {
                throw new Exception("Only lead can invite core team member");
            }

            LeadEntity leadEntity = leadService.getLeadEntityFromUserEntity(userEntity);

            UniversityEntity universityEntity = leadEntity.getUniversity();

            if (universityEntity == null) {
                throw new Exception("Lead has no university");
            }


            String email = inviteCoreTeamRequest.getEmail();



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
            return coreTeamMapper.toDTO(coreTeamMemberEntities);
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

            return coreTeamMapper.toDTO(savedCoreTeamMemberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



}
