package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.AnnouncementDTO;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.CreateAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.EditAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.GetAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.campaign.GetCampaignRequest;
import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.entity.CampaignEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.AnnouncementNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.CampaignNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.AnnouncementMapper;
import com.tolgaozgun.gdscturkweb.model.Announcement;
import com.tolgaozgun.gdscturkweb.model.Campaign;
import com.tolgaozgun.gdscturkweb.repository.AnnouncementRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public List<AnnouncementDTO> getAllAnnouncementsByUserType(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String userName = authentication.getName();

            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();

            UserType userType = userEntity.getUserType();

            Date date = new Date();

            List<AnnouncementEntity> announcementEntities = announcementRepository.findAllByPermittedUserTypesContainingAndStartDateIsBeforeAndEndDateIsAfter(userType, date, date);

            return announcementMapper.toDTO(announcementEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public List<AnnouncementDTO> getAllAnnouncements(){
        try{
            List<AnnouncementEntity> announcementEntities = announcementRepository.findAll();

            return announcementMapper.toDTO(announcementEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }


    public AnnouncementDTO getAnnouncementById(GetAnnouncementRequest getAnnouncementRequest) {
        try {

            Long announcementId = getAnnouncementRequest.getAnnouncementId();
            Optional<AnnouncementEntity> optionalAnnouncementEntity = announcementRepository.findById(announcementId);

            if (optionalAnnouncementEntity.isEmpty()) {
                throw new AnnouncementNotFoundException(announcementId);
            }

            AnnouncementEntity announcementEntity = optionalAnnouncementEntity.get();
            return announcementMapper.toDTO(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public AnnouncementDTO createAnnouncement(CreateAnnouncementRequest announcementRequest){
        try {
            AnnouncementEntity announcementEntity = new AnnouncementEntity();

            String title = announcementRequest.getTitle();
            String description = announcementRequest.getDescription();
            Date startDate = announcementRequest.getStartDate();
            Date endDate = announcementRequest.getEndDate();
            List<UserType> permittedUserTypes = announcementRequest.getPermittedUserTypes();

            if (permittedUserTypes == null || permittedUserTypes.isEmpty()) {
                List<UserType> userTypes = new ArrayList<>();
                userTypes.add(UserType.FACILITATOR);
                userTypes.add(UserType.LEAD);
                userTypes.add(UserType.CORE_TEAM_MEMBER);
                userTypes.add(UserType.GOOGLER);
                userTypes.add(UserType.ADMIN);
                announcementEntity.setPermittedUserTypes(userTypes);
            }

            announcementEntity.setTitle(title);
            announcementEntity.setDescription(description);
            announcementEntity.setStartDate(startDate);
            announcementEntity.setEndDate(endDate);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            announcementEntity.setSentBy(userEntity);
            announcementEntity = announcementRepository.save(announcementEntity);

            return announcementMapper.toDTO(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public AnnouncementDTO editAnnouncement(EditAnnouncementRequest editAnnouncementRequest){
        try {
            Long announcementId = editAnnouncementRequest.getAnnouncementId();

            Optional<AnnouncementEntity> optionalAnnouncementEntity = announcementRepository.findById(announcementId);

            if (optionalAnnouncementEntity.isEmpty()) {
                throw new AnnouncementNotFoundException();
            }

            AnnouncementEntity announcementEntity = optionalAnnouncementEntity.get();

            if (editAnnouncementRequest.getTitle() != null) {
                announcementEntity.setTitle(editAnnouncementRequest.getTitle());
            }

            if (editAnnouncementRequest.getDescription() != null) {
                announcementEntity.setDescription(editAnnouncementRequest.getDescription());
            }

            if (editAnnouncementRequest.getStartDate() != null) {
                announcementEntity.setStartDate(editAnnouncementRequest.getStartDate());
            }

            if (editAnnouncementRequest.getEndDate() != null) {
                announcementEntity.setEndDate(editAnnouncementRequest.getEndDate());
            }

            if (editAnnouncementRequest.getPermittedUserTypes() != null && !editAnnouncementRequest.getPermittedUserTypes().isEmpty()) {
                announcementEntity.setPermittedUserTypes(editAnnouncementRequest.getPermittedUserTypes());
            }

            announcementEntity = announcementRepository.save(announcementEntity);

            return announcementMapper.toDTO(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
