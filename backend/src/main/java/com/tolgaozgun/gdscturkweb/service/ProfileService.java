package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.*;
import com.tolgaozgun.gdscturkweb.dto.request.profile.*;
import com.tolgaozgun.gdscturkweb.dto.user.profile.*;
import com.tolgaozgun.gdscturkweb.entity.*;
import com.tolgaozgun.gdscturkweb.entity.user.*;
import com.tolgaozgun.gdscturkweb.exception.*;
import com.tolgaozgun.gdscturkweb.mapper.*;
import com.tolgaozgun.gdscturkweb.repository.*;
import com.tolgaozgun.gdscturkweb.repository.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final CoreTeamMemberMapper coreTeamMemberMapper;
    private final GooglerMapper googlerMapper;
    private final UserMapper userMapper;

    private final AuthService authService;


    private UserEntity checkAndUpdateUserProfileByStaff(Long userId, UpdateUserProfileByStaff updateUserProfileByStaff) {

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

        if (updateUserProfile.getBiography() != null) {
            userEntity.setBiography(updateUserProfile.getBiography());
        }

        if (updateUserProfile.getInterests() != null) {
            userEntity.setInterests(topicMapper.toEntity(updateUserProfile.getInterests()));
        }

        return userRepository.save(userEntity);
    }

    public UserDTO updateUserProfileByUser(UpdateUserProfileByUserRequest updateUserProfileByUserRequest) {
        try{
            UpdateUserProfileByUser updateUserProfile = updateUserProfileByUserRequest.getUpdateUserProfile();
            UserEntity userEntity = checkAndUpdateUserProfile(updateUserProfile);
            return userMapper.toDTO(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserDTO updateUserProfileByStaff(Long userId,
                                            UpdateUserProfileByStaffRequest updateUserProfileByStaffRequest) {
        try {
            UpdateUserProfileByStaff updateUserProfileByStaff = updateUserProfileByStaffRequest.getUpdateUserProfile();
            UserEntity userEntity = checkAndUpdateUserProfileByStaff(userId, updateUserProfileByStaff);
            return userMapper.toDTO(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public FacilitatorDTO updateFacilitatorProfileByStaff(Long facilitatorId, UpdateFacilitatorProfileByStaffRequest
                                                                  updateFacilitatorProfileByStaffRequest)
            throws Exception {
        try {

            UpdateFacilitatorProfileByStaff updateFacilitatorProfileByStaff = updateFacilitatorProfileByStaffRequest.getUpdateFacilitatorProfile();

            Optional<FacilitatorEntity> optionalFacilitatorEntity = facilitatorRepository.findById(facilitatorId);

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

            UpdateFacilitatorProfileByFacilitator updateFacilitatorProfile = updateFacilitatorProfileRequest.getUpdateFacilitatorProfile();

            UserEntity savedEntity = authService.getCurrentUserEntity();

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
    public GooglerDTO updateGooglerProfileByStaff(Long googlerId,
                                                  UpdateGooglerProfileByStaffRequest updateGooglerProfileByStaffRequest) {

        UpdateGooglerProfileByStaff updateGooglerProfileByStaff = updateGooglerProfileByStaffRequest.getUpdateGooglerProfile();

        Optional<GooglerEntity> optionalGooglerEntity = googlerRepository.findById(googlerId);

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

        UpdateGooglerProfileByGoogler updateGooglerProfile = updateGooglerProfileByGooglerRequest.getUpdateGooglerProfile();

        UserEntity savedEntity = authService.getCurrentUserEntity();

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

    public LeadDTO updateLeadProfileByStaff(Long leadId, UpdateLeadProfileByStaffRequest updateLeadProfileByStaffRequest) {

        UpdateLeadProfileByStaff updateLeadProfileByStaff = updateLeadProfileByStaffRequest.getUpdateLeadProfile();

        Optional<LeadEntity> optionalLeadEntity = leadRepository.findById(leadId);

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

        UpdateLeadProfileByLead updateLeadProfile = updateLeadProfileRequest.getUpdateLeadProfile();

        UserEntity savedEntity = authService.getCurrentUserEntity();

        Optional<LeadEntity> optionalLeadEntity = leadRepository.findByUser(savedEntity);

        if (optionalLeadEntity.isEmpty()) {
            throw new LeadNotFoundException();
        }

        LeadEntity leadEntity = optionalLeadEntity.get();

        return leadMapper.toDTO(leadEntity);


    }

    public CoreTeamMemberDTO updateCoreTeamMemberProfileByStaff(Long coreTeamMemberId,
                                                                UpdateCoreTeamMemberProfileByStaffRequest
                                                                 updateCoreTeamMemberProfileByStaffRequest) {

        UpdateCoreTeamProfileByStaff updateCoreTeamProfileByStaff = updateCoreTeamMemberProfileByStaffRequest.getUpdateCoreTeamProfile();

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

        return coreTeamMemberMapper.toDTO(coreTeamMemberEntity);
    }

    public CoreTeamMemberDTO updateCoreTeamMemberProfile(UpdateCoreTeamMemberProfileByMemberRequest
                                                                 updateCoreTeamMemberProfileRequest) {

        UpdateCoreTeamProfileByCoreTeam updateCoreTeamProfile = updateCoreTeamMemberProfileRequest.getUpdateCoreTeamProfile();

        UserEntity savedEntity = authService.getCurrentUserEntity();

        Optional<CoreTeamMemberEntity> optionalCoreTeamMemberEntity = coreTeamMemberRepository.findByUser(savedEntity);

        if (optionalCoreTeamMemberEntity.isEmpty()) {
            throw new LeadNotFoundException();
        }

        CoreTeamMemberEntity coreTeamMemberEntity = optionalCoreTeamMemberEntity.get();

        return coreTeamMemberMapper.toDTO(coreTeamMemberEntity);
    }
}
