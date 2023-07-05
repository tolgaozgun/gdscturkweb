package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
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

    public List<CoreTeamMemberDTO> getAllCoreTeamMembers() {
        try {
            List<CoreTeamMemberEntity> coreTeamMemberEntities = coreTeamMemberRepository.findAll();
            return coreTeamMapper.toDTO(coreTeamMemberEntities);
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


}
