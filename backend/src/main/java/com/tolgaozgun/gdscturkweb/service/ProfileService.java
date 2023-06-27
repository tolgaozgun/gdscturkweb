package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.profile.*;
import com.tolgaozgun.gdscturkweb.dto.user.profile.*;
import com.tolgaozgun.gdscturkweb.entity.BuddyTeamEntity;
import com.tolgaozgun.gdscturkweb.entity.UniversityEntity;
import com.tolgaozgun.gdscturkweb.entity.user.CoreTeamMemberEntity;
import com.tolgaozgun.gdscturkweb.entity.user.FacilitatorEntity;
import com.tolgaozgun.gdscturkweb.entity.user.LeadEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.*;
import com.tolgaozgun.gdscturkweb.mapper.CoreTeamMapper;
import com.tolgaozgun.gdscturkweb.mapper.FacilitatorMapper;
import com.tolgaozgun.gdscturkweb.mapper.LeadMapper;
import com.tolgaozgun.gdscturkweb.mapper.TopicMapper;
import com.tolgaozgun.gdscturkweb.repository.BuddyTeamRepository;
import com.tolgaozgun.gdscturkweb.repository.UniversityRepository;
import com.tolgaozgun.gdscturkweb.repository.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final UserRepository userRepository;

    private final LeadRepository leadRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final GooglerRepository googlerRepository;
    private final AdminRepository adminRepository;
    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;

    private final TopicMapper topicMapper;
    private final FacilitatorMapper facilitatorMapper;
    private final LeadMapper leadMapper;
    private final CoreTeamMapper coreTeamMapper;

    private UserEntity checkAndUpdateUserProfileByStaff(UpdateUserProfileByStaff updateUserProfileByStaff) {

        Long userId = updateUserProfileByStaff.getUserId();

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

        if (optionalUserEntity.isEmpty()) {
            throw new UserNotFoundException("Error while getting user details");
        }

        UserEntity userEntity = optionalUserEntity.get();
        if (updateUserProfileByStaff.getProfileImage() != null) {
            userEntity.setProfileImage(updateUserProfileByStaff.getProfileImage());
        }

        if (updateUserProfileByStaff.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(updateUserProfileByStaff.getPhoneNumber());
        }

        if (updateUserProfileByStaff.getName() != null) {
            userEntity.setName(updateUserProfileByStaff.getName());
        }

        if (updateUserProfileByStaff.getSurname() != null) {
            userEntity.setSurname(updateUserProfileByStaff.getSurname());
        }

        if (updateUserProfileByStaff.getBiography() != null) {
            userEntity.setBiography(updateUserProfileByStaff.getBiography());
        }

        if (updateUserProfileByStaff.getInterests() != null) {
            userEntity.setInterests(topicMapper.toEntity(updateUserProfileByStaff.getInterests()));
        }

        // Extra for staff

        if (updateUserProfileByStaff.getEmail() != null) {
            userEntity.setEmail(updateUserProfileByStaff.getEmail());
        }

        return userRepository.save(userEntity);
    }

    private UserEntity checkAndUpdateUserProfile(UpdateUserProfileByUser updateUserProfile) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

        if (optionalUserEntity.isEmpty()) {
            throw new UserNotFoundException("Error while getting user details");
        }

        UserEntity userEntity = optionalUserEntity.get();
        if (updateUserProfile.getProfileImage() != null) {
            userEntity.setProfileImage(updateUserProfile.getProfileImage());
        }

        if (updateUserProfile.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(updateUserProfile.getPhoneNumber());
        }

        if (updateUserProfile.getName() != null) {
            userEntity.setName(updateUserProfile.getName());
        }

        if (updateUserProfile.getSurname() != null) {
            userEntity.setSurname(updateUserProfile.getSurname());
        }

        if (updateUserProfile.getBiograpghy() != null) {
            userEntity.setBiography(updateUserProfile.getBiograpghy());
        }

        if (updateUserProfile.getInterests() != null) {
            userEntity.setInterests(topicMapper.toEntity(updateUserProfile.getInterests()));
        }

        return userRepository.save(userEntity);
    }

    public FacilitatorDTO updateFacilitatorProfileByStaff(UpdateFacilitatorProfileByStaffRequest
                                                                  updateFacilitatorProfileByStaffRequest)
            throws Exception {
        try {

            UpdateUserProfileByStaff updateUserProfileByStaff = updateFacilitatorProfileByStaffRequest.getUpdateUserProfile();
            UpdateFacilitatorProfileByStaff updateFacilitatorProfileByStaff = updateFacilitatorProfileByStaffRequest.getUpdateFacilitatorProfile();

            UserEntity savedEntity = checkAndUpdateUserProfileByStaff(updateUserProfileByStaff);

            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(savedEntity);

            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }

            FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

            if (updateFacilitatorProfileByStaff.getUniversityId() != null) {

                UniversityEntity universityEntity = universityRepository.findById(updateFacilitatorProfileByStaff.getUniversityId())
                        .orElseThrow(() -> new Exception("University not found"));

                facilitatorEntity.setUniversity(universityEntity);
            }

            facilitatorEntity = facilitatorRepository.save(facilitatorEntity);

            return facilitatorMapper.toDTO(facilitatorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public FacilitatorDTO updateFacilitatorProfile(UpdateFacilitatorProfileByFacilitatorRequest updateFacilitatorProfileRequest)
            throws Exception {
        try {

            UpdateUserProfileByUser userRegister = updateFacilitatorProfileRequest.getUpdateUserProfile();
            UpdateFacilitatorProfileByFacilitator updateFacilitatorProfile = updateFacilitatorProfileRequest.getUpdateFacilitatorProfile();

            UserEntity savedEntity = checkAndUpdateUserProfile(userRegister);

            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findByUser(savedEntity);

            if (optionalFacilitatorEntity.isEmpty()) {
                throw new FacilitatorNotFoundException();
            }

            FacilitatorEntity facilitatorEntity = optionalFacilitatorEntity.get();

//            if (updateFacilitatorProfile.getUniversityId() != null) {
//
//                UniversityEntity universityEntity = universityRepository.findById(updateFacilitatorProfile.getUniversityId())
//                        .orElseThrow(() -> new Exception("University not found"));
//
//                facilitatorEntity.setUniversity(universityEntity);
//            }
//
//            facilitatorEntity = facilitatorRepository.save(facilitatorEntity);

            return facilitatorMapper.toDTO(facilitatorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public LeadDTO updateLeadProfileByStaff(UpdateLeadProfileByStaffRequest updateLeadProfileByStaffRequest) {

        UpdateUserProfileByStaff updateUserProfileByStaff = updateLeadProfileByStaffRequest.getUpdateUserProfile();
        UpdateLeadProfileByStaff updateLeadProfileByStaff = updateLeadProfileByStaffRequest.getUpdateLeadProfile();

        UserEntity savedEntity = checkAndUpdateUserProfileByStaff(updateUserProfileByStaff);

        Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(updateLeadProfileByStaff.getLeadId());

        if (optionalLeadEntity.isEmpty()) {
            throw new LeadNotFoundException();
        }

        LeadEntity leadEntity = optionalLeadEntity.get();

        if (updateLeadProfileByStaff.getBuddyTeamId() != null) {
            Long buddyTeamId = updateLeadProfileByStaff.getBuddyTeamId();
            Optional<BuddyTeamEntity> optionalBuddyTeamEntity = buddyTeamRepository.findById(buddyTeamId);

            if (optionalBuddyTeamEntity.isEmpty()) {
                throw new BuddyTeamNotFoundException();
            }

            BuddyTeamEntity buddyTeamEntity = optionalBuddyTeamEntity.get();
            leadEntity.setBuddyTeam(buddyTeamEntity);
        }

        if (updateLeadProfileByStaff.getUniversityId() != null) {
            Long universityId = updateLeadProfileByStaff.getUniversityId();
            Optional<UniversityEntity> optionalUniversityEntity = universityRepository.findById(universityId);

            if (optionalUniversityEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            UniversityEntity universityEntity = optionalUniversityEntity.get();
            leadEntity.setUniversity(universityEntity);
        }



        return leadMapper.toDTO(leadRepository.save(leadEntity));


    }

    public LeadDTO updateLeadProfile(UpdateLeadProfileByLeadRequest updateLeadProfileRequest) {

        UpdateUserProfileByUser userRegister = updateLeadProfileRequest.getUpdateUserProfile();
        UpdateLeadProfileByLead updateLeadProfile = updateLeadProfileRequest.getUpdateLeadProfile();

        UserEntity savedEntity = checkAndUpdateUserProfile(userRegister);

        Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(savedEntity);

        if (optionalLeadEntity.isEmpty()) {
            throw new LeadNotFoundException();
        }

        LeadEntity leadEntity = optionalLeadEntity.get();

        return leadMapper.toDTO(leadEntity);


    }

    public CoreTeamMemberDTO updateCoreTeamMemberProfileByStaff(UpdateCoreTeamMemberProfileByStaffRequest
                                                                 updateCoreTeamMemberProfileByStaffRequest) {

        UpdateUserProfileByStaff updateUserProfileByStaff = updateCoreTeamMemberProfileByStaffRequest.getUpdateUserProfile();
        UpdateCoreTeamProfileByStaff updateCoreTeamProfileByStaff = updateCoreTeamMemberProfileByStaffRequest.getUpdateCoreTeamProfile();

        UserEntity savedEntity = checkAndUpdateUserProfileByStaff(updateUserProfileByStaff);

        Long coreTeamMemberId = updateCoreTeamProfileByStaff.getCoreTeamMemberId();

        Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntity = coreTeamMemberRepository.findById(coreTeamMemberId);

        if (optionalCoreTeamMemberEntity.isEmpty()) {
            throw new CoreTeamMemberNotFoundException();
        }

        CoreTeamMemberEntity coreTeamMemberEntity = optionalCoreTeamMemberEntity.get();

        if (updateCoreTeamProfileByStaff.getUniversityId() != null) {
            Long universityId = updateCoreTeamProfileByStaff.getUniversityId();
            Optional<UniversityEntity> optionalUniversityEntity = universityRepository.findById(universityId);

            if (optionalUniversityEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            UniversityEntity universityEntity = optionalUniversityEntity.get();
            coreTeamMemberEntity.setUniversity(universityEntity);
        }

        coreTeamMemberEntity = coreTeamMemberRepository.save(coreTeamMemberEntity);

        return coreTeamMapper.toDTO(coreTeamMemberEntity);
    }

    public CoreTeamMemberDTO updateCoreTeamMemberProfile(UpdateCoreTeamMemberProfileByMemberRequest
                                                                 updateCoreTeamMemberProfileRequest) {

        UpdateUserProfileByUser userRegister = updateCoreTeamMemberProfileRequest.getUpdateUserProfile();
        UpdateCoreTeamProfileByCoreTeam updateCoreTeamProfile = updateCoreTeamMemberProfileRequest.getUpdateCoreTeamProfile();

        UserEntity savedEntity = checkAndUpdateUserProfile(userRegister);

        Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntity = coreTeamMemberRepository.findByUser(savedEntity);

        if (optionalCoreTeamMemberEntity.isEmpty()) {
            throw new LeadNotFoundException();
        }

        CoreTeamMemberEntity coreTeamMemberEntity = optionalCoreTeamMemberEntity.get();

        return coreTeamMapper.toDTO(coreTeamMemberEntity);
    }
}
