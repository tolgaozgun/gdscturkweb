package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.register.FacilitatorRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.user.register.FacilitatorRegister;
import com.tolgaozgun.gdscturkweb.dto.user.register.UserRegister;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.mapper.FacilitatorMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.FacilitatorRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacilitatorService {

    private final AuthService authService;

    private final UniversityRepository universityRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    private final FacilitatorMapper facilitatorMapper;

    public List<FacilitatorDTO> getAllFacilitators() {
        try {
            List<FacilitatorEntity> facilitatorEntities = facilitatorRepository.findAll();
            return facilitatorMapper.toDTO(facilitatorEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
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


}
