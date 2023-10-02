package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.request.buddyTeam.UpdateBuddyTeamRequest;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.BuddyTeamNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.FacilitatorNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.LeadNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.BuddyTeamMapper;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.user.FacilitatorRepository;
import com.tolgaozgun.gdscturkweb.repository.user.LeadRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BuddyTeamService {

    private final BuddyTeamRepository buddyTeamRepository;
    private final UserRepository userRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final LeadRepository leadRepository;

    private final BuddyTeamMapper buddyTeamMapper;

    private final AuthService authService;

    public List<BuddyTeamDTO> getAllBuddyTeams() {
        try {
            return buddyTeamMapper.toDTO(buddyTeamRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BuddyTeamDTO updateBuddyTeamByLead(UpdateBuddyTeamRequest updateBuddyTeamRequest) throws Exception {
        try {
            BuddyTeamEntity buddyTeamEntity = getBuddyTeamEntityByCurrentUser();
            return updateBuddyTeamByEntity(buddyTeamEntity, updateBuddyTeamRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BuddyTeamDTO updateBuddyTeamByFacilitator(UpdateBuddyTeamRequest updateBuddyTeamRequest) throws Exception {
        try {
            BuddyTeamEntity buddyTeamEntity = getBuddyTeamEntityByFacilitator();
            return updateBuddyTeamByEntity(buddyTeamEntity, updateBuddyTeamRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public BuddyTeamDTO updateBuddyTeamByEntity(BuddyTeamEntity buddyTeamEntity, UpdateBuddyTeamRequest updateBuddyTeamRequest) throws Exception {
        try {

            if (updateBuddyTeamRequest.getFacilitatorId() != null) {
                System.out.println("Facilitator id is not null");
                Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findById(updateBuddyTeamRequest.getFacilitatorId());

                if (optionalFacilitatorEntity.isEmpty()) {
                    throw new FacilitatorNotFoundException();
                }

                FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

                buddyTeamEntity.setFacilitator(facilitatorEntity);
            }

            if (updateBuddyTeamRequest.getLeadIds() != null && !updateBuddyTeamRequest.getLeadIds().isEmpty()) {
                System.out.println("Lead ids are not null");
                List<LeadEntity> leadEntities = leadRepository.findAllById(updateBuddyTeamRequest.getLeadIds());

                if (leadEntities.size() != updateBuddyTeamRequest.getLeadIds().size()) {
                    throw new LeadNotFoundException();
                }


                buddyTeamEntity.setLeads(leadEntities);
            }

            if (updateBuddyTeamRequest.getName() != null) {
                if (updateBuddyTeamRequest.getName().isEmpty()) {
                    throw new Exception("Buddy team name cannot be empty");
                }
                buddyTeamEntity.setName(updateBuddyTeamRequest.getName());

            }
            buddyTeamRepository.save(buddyTeamEntity);

            return buddyTeamMapper.toDTO(buddyTeamEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BuddyTeamDTO updateBuddyTeamById(Long buddyTeamId, UpdateBuddyTeamRequest updateBuddyTeamRequest) throws Exception {
        try {
            Optional<BuddyTeamEntity> optionalBuddyTeamEntity = buddyTeamRepository.findById(buddyTeamId);

            if (optionalBuddyTeamEntity.isEmpty()) {
                throw new BuddyTeamNotFoundException();
            }

            BuddyTeamEntity buddyTeamEntity = optionalBuddyTeamEntity.get();
            return updateBuddyTeamByEntity(buddyTeamEntity, updateBuddyTeamRequest);
        } catch (Exception e) {
            e.printStackTrace();;
            throw e;
        }
    }

    public BuddyTeamEntity getBuddyTeamEntityByCurrentUser() {
        try {

            UserEntity userEntity = authService.getCurrentUserEntity();

            Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(userEntity);

            if (optionalLeadEntity.isEmpty()) {
                throw new LeadNotFoundException();
            }

            LeadEntity leadEntity = optionalLeadEntity.get();

            return buddyTeamRepository.findByLeadsContains(leadEntity)
                    .orElseThrow(BuddyTeamNotFoundException::new);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public BuddyTeamDTO getBuddyTeamDTOByCurrentUser() {
        try {
            BuddyTeamEntity buddyTeamEntity = getBuddyTeamEntityByCurrentUser();

            return buddyTeamMapper.toDTO(buddyTeamEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public BuddyTeamEntity getBuddyTeamEntityByFacilitator() {
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(userEntity);

            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }

            FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

            return buddyTeamRepository.findByFacilitator(facilitatorEntity)
                    .orElseThrow(BuddyTeamNotFoundException::new);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public BuddyTeamDTO getBuddyTeamDTOByFacilitator() {
        try {
            BuddyTeamEntity buddyTeamEntity = getBuddyTeamEntityByFacilitator();

            return buddyTeamMapper.toDTO(buddyTeamEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }
}
