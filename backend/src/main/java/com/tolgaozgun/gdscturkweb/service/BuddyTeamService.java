package com.tolgaozgun.gdscturkweb.service;


import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.BuddyTeamNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.FacilitatorNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.LeadNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.BuddyTeamMapper;
import com.tolgaozgun.gdscturkweb.model.user.Lead;
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
    private final BuddyTeamMapper buddyTeamMapper;
    private final FacilitatorRepository facilitatorRepository;
    private final LeadRepository leadRepository;

    public List<BuddyTeamDTO> getAllBuddyTeams() {
        try {
            return buddyTeamMapper.toDTO(buddyTeamRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BuddyTeamDTO getBuddyTeamByCurrentUser() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(userEntity);

            if (optionalLeadEntity.isEmpty()) {
                throw new LeadNotFoundException();
            }

            LeadEntity leadEntity = optionalLeadEntity.get();

            BuddyTeamEntity buddyTeamEntity = buddyTeamRepository.findByLeadsContains(leadEntity)
                    .orElseThrow(BuddyTeamNotFoundException::new);

            return buddyTeamMapper.toDTO(buddyTeamEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public BuddyTeamDTO getBuddyTeamByFacilitator(Long facilitatorId) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(facilitatorId);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(userEntity);

            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }

            FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

            BuddyTeamEntity buddyTeamEntity = buddyTeamRepository.findByFacilitator(facilitatorEntity)
                    .orElseThrow(BuddyTeamNotFoundException::new);

            return buddyTeamMapper.toDTO(buddyTeamEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }
}
