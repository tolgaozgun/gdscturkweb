package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.GooglerDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.profile.*;
import com.tolgaozgun.gdscturkweb.dto.user.profile.*;
import com.tolgaozgun.gdscturkweb.entity.*;
import com.tolgaozgun.gdscturkweb.entity.user.*;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.*;
import com.tolgaozgun.gdscturkweb.mapper.*;
import com.tolgaozgun.gdscturkweb.model.Permission;
import com.tolgaozgun.gdscturkweb.repository.*;
import com.tolgaozgun.gdscturkweb.repository.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final UserRepository userRepository;

    private final LeadRepository leadRepository;
    private final FacilitatorRepository facilitatorRepository;
    private final GooglerRepository googlerRepository;
    private final CoreTeamMemberRepository coreTeamMemberRepository;
    private final UniversityRepository universityRepository;
    private final BuddyTeamRepository buddyTeamRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    private final TopicMapper topicMapper;
    private final FacilitatorMapper facilitatorMapper;
    private final LeadMapper leadMapper;
    private final CoreTeamMapper coreTeamMapper;
    private final GooglerMapper googlerMapper;

    private final AuthService authService;


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
        UserEntity userEntity = authService.getCurrentUserEntity();
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

            return facilitatorMapper.toDTO(facilitatorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public GooglerDTO updateGooglerProfileByStaff(UpdateGooglerProfileByStaffRequest updateGooglerProfileByStaffRequest) {

        UpdateUserProfileByStaff updateUserProfileByStaff = updateGooglerProfileByStaffRequest.getUpdateUserProfile();
        UpdateGooglerProfileByStaff updateGooglerProfileByStaff = updateGooglerProfileByStaffRequest.getUpdateGooglerProfile();

        UserEntity savedEntity = checkAndUpdateUserProfileByStaff(updateUserProfileByStaff);

        Optional<GooglerEntity> optionalGooglerEntity = googlerRepository.findById(updateGooglerProfileByStaff.getGooglerId());

        if (optionalGooglerEntity.isEmpty()) {
            throw new GooglerNotFoundException();
        }

        GooglerEntity googlerEntity = optionalGooglerEntity.get();

        if (updateGooglerProfileByStaff.getCityId() != null) {
            Long cityId = updateGooglerProfileByStaff.getCityId();
            Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

            if (optionalCityEntity.isEmpty()) {
                throw new CityNotFoundException();
            }

            CityEntity cityEntity = optionalCityEntity.get();
            googlerEntity.setCity(cityEntity);
        }

        if (updateGooglerProfileByStaff.getCountryId() != null) {
            Long countryId = updateGooglerProfileByStaff.getCountryId();
            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();
            googlerEntity.setCountry(countryEntity);
        }

        return googlerMapper.toDTO(googlerRepository.save(googlerEntity));

    }

    public GooglerDTO updateGooglerProfile(UpdateGooglerProfileByGooglerRequest updateGooglerProfileByGooglerRequest) {

        UpdateUserProfileByUser userRegister = updateGooglerProfileByGooglerRequest.getUpdateUserProfile();
        UpdateGooglerProfileByGoogler updateGooglerProfile = updateGooglerProfileByGooglerRequest.getUpdateGooglerProfile();

        UserEntity savedEntity = checkAndUpdateUserProfile(userRegister);

        Optional<GooglerEntity> optionalGooglerEntity = googlerRepository.findByUser(savedEntity);

        if (optionalGooglerEntity.isEmpty()) {
            throw new GooglerNotFoundException();
        }

        GooglerEntity googlerEntity = optionalGooglerEntity.get();

        if (updateGooglerProfile.getCityId() != null) {
            Long cityId = updateGooglerProfile.getCityId();
            Optional<CityEntity> optionalCityEntity = cityRepository.findById(cityId);

            if (optionalCityEntity.isEmpty()) {
                throw new CityNotFoundException();
            }

            CityEntity cityEntity = optionalCityEntity.get();
            googlerEntity.setCity(cityEntity);
        }

        if (updateGooglerProfile.getCountryId() != null) {
            Long countryId = updateGooglerProfile.getCountryId();
            Optional<CountryEntity> optionalCountryEntity = countryRepository.findById(countryId);

            if (optionalCountryEntity.isEmpty()) {
                throw new UniversityNotFoundException();
            }

            CountryEntity countryEntity = optionalCountryEntity.get();
            googlerEntity.setCountry(countryEntity);
        }

        return googlerMapper.toDTO(googlerRepository.save(googlerEntity));


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
