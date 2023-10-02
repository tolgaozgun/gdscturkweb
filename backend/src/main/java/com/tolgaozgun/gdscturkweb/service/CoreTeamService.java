package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.CoreTeamDTO;
import com.tolgaozgun.gdscturkweb.entity.CoreTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.CoreTeamNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.CoreTeamMapper;
import com.tolgaozgun.gdscturkweb.repository.CoreTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoreTeamService {

    private final AuthService authService;
    private final CoreTeamMemberService coreTeamMemberService;

    private final CoreTeamRepository coreTeamRepository;

    private final CoreTeamMapper coreTeamMapper;

    protected CoreTeamEntity createCoreTeam(LeadEntity leadEntity) {
        try {
            CoreTeamEntity coreTeamEntity = new CoreTeamEntity();
            coreTeamEntity.setLead(leadEntity);
            coreTeamEntity.setUniversity(leadEntity.getUniversity());
            coreTeamEntity.setName(leadEntity.getUniversity().getName() + "'s Core Team");

            return coreTeamRepository.save(coreTeamEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public List<CoreTeamDTO> getAllCoreTeams() {
        try {
            return coreTeamMapper.toDTO(coreTeamRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CoreTeamDTO getCoreTeamById(Long coreTeamId) {
        try {
            Optional<CoreTeamEntity> optCoreTeamEntity = coreTeamRepository.findById(coreTeamId);

            if (optCoreTeamEntity.isEmpty()) {
                throw new CoreTeamNotFoundException();
            }

            CoreTeamEntity coreTeamEntity = optCoreTeamEntity.get();

            return coreTeamMapper.toDTO(coreTeamEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CoreTeamDTO getCoreTeamByCurrentLead(LeadEntity leadEntity) {
        try {

            Optional<CoreTeamEntity> optCoreTeamEntity = coreTeamRepository.findCoreTeamEntityByLead(leadEntity);

            if (optCoreTeamEntity.isEmpty()) {
                throw new CoreTeamNotFoundException();
            }

            CoreTeamEntity coreTeamEntity = optCoreTeamEntity.get();

            return coreTeamMapper.toDTO(coreTeamEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public CoreTeamDTO getCoreTeamByCurrentCoreTeamMember() throws Exception {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();
            CoreTeamMemberEntity coreTeamMemberEntity = coreTeamMemberService.getCoreTeamMemberEntity(userEntity);

            CoreTeamEntity coreTeamEntity = coreTeamMemberEntity.getCoreTeam();

            if (coreTeamEntity == null) {
                throw new CoreTeamNotFoundException();
            }

            return coreTeamMapper.toDTO(coreTeamEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

}
