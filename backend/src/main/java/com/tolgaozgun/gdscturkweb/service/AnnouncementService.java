package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.request.AnnouncementRequest;
import com.tolgaozgun.gdscturkweb.entity.AnnouncementEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.UserNotFoundException;
import com.tolgaozgun.gdscturkweb.mapper.AnnouncementMapper;
import com.tolgaozgun.gdscturkweb.model.Announcement;
import com.tolgaozgun.gdscturkweb.repository.AnnouncementRepository;
import com.tolgaozgun.gdscturkweb.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public List<Announcement> getAllAnnouncementsByUserType(){
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

            return announcementMapper.toModel(announcementEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public List<Announcement> getAllAnnouncements(){
        try{
            List<AnnouncementEntity> announcementEntities = announcementRepository.findAll();

            return announcementMapper.toModel(announcementEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    public Announcement createAnnouncement(AnnouncementRequest announcementRequest){
        try {
            AnnouncementEntity announcementEntity = announcementMapper.toEntity(announcementRequest);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userName);

            if (optionalUserEntity.isEmpty()) {
                throw new UserNotFoundException("Error while getting user details");
            }

            UserEntity userEntity = optionalUserEntity.get();
            announcementEntity.setSentBy(userEntity);
            announcementEntity = announcementRepository.save(announcementEntity);

            return announcementMapper.toModel(announcementEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
