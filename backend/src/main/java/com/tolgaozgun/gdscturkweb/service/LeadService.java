package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.user.register.LeadRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.mapper.UniversityMapper;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final UniversityRepository universityRepository;

    private final LeadMapper leadMapper;

    private final AuthService authService;

    public List<LeadDTO> getAllLeads() {
        try {
            List<LeadEntity> leadEntities = leadRepository.findAll();
            return leadMapper.toDTO(leadEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public LeadDTO registerLead(LeadRegisterRequest leadRegisterRequest) throws Exception {
        try {

            UserRegister userRegister = leadRegisterRequest.getUserRegister();
            LeadRegister leadRegister = leadRegisterRequest.getLeadRegister();

            UserEntity savedEntity = authService.checkAndRegisterUser(userRegister, UserType.LEAD);

            UniversityEntity universityEntity = universityRepository.findById(leadRegister.getUniversityId())
                    .orElseThrow(() -> new Exception("University not found"));

            if (leadRepository.existsLeadEntityByUniversity(universityEntity)) {
                throw new Exception("University already has a lead");
            }

            LeadEntity leadEntity = new LeadEntity(universityEntity, savedEntity);

            LeadEntity savedLeadEntity = leadRepository.save(leadEntity);

            return leadMapper.toDTO(savedLeadEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public LeadDTO createLead() {
        try {


        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }


}
