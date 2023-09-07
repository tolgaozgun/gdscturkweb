package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.AnnouncementDTO;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.CreateAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.EditAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.AnnouncementNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.AnnouncementMapper;
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

    private final AuthService authService;

    public List<AnnouncementDTO> getAllAnnouncementsByUserType(){
        try {
            UserEntity userEntity = authService.getCurrentUserEntity();

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


    public AnnouncementDTO getAnnouncementById(Long announcementId) {
        try {

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

            UserEntity userEntity = authService.getCurrentUserEntity();
            announcementEntity.setSentBy(userEntity);
            announcementEntity = announcementRepository.save(announcementEntity);

            return announcementMapper.toDTO(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public AnnouncementDTO editAnnouncement(Long announcementId, EditAnnouncementRequest editAnnouncementRequest){
        try {

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
