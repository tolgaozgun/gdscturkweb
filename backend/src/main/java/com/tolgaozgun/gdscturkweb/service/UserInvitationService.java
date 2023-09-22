package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.request.invitation.InviteUserRequest;
import com.tolgaozgun.gdscturkweb.dto.response.UserInvitationResponse;
import com.tolgaozgun.gdscturkweb.entity.UserInviteEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.enums.UserType;
import com.tolgaozgun.gdscturkweb.exception.NotPermittedException;
import com.tolgaozgun.gdscturkweb.exception.UserAlreadyExistsException;
import com.tolgaozgun.gdscturkweb.exception.invitation.InvitationIsExpiredException;
import com.tolgaozgun.gdscturkweb.exception.invitation.InvitationIsNotValidException;
import com.tolgaozgun.gdscturkweb.exception.invitation.InvitationNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.invitation.InvitationRoleMismatchException;
import com.tolgaozgun.gdscturkweb.repository.UserInvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserInvitationService {

    private final UserInvitationRepository userInvitationRepository;
    private final UserService userService;
    private final EmailService emailService;

    private final int VALIDITY_IN_DAYS = 7;

    public UserInvitationResponse cancelInvitation(Long invitationId) {
        try{
            Optional<UserInviteEntity> userInviteEntity = userInvitationRepository.findById(invitationId);

            if (userInviteEntity.isEmpty()) {
                throw new InvitationNotFoundException();
            }

            UserInviteEntity userInvite = userInviteEntity.get();
            userInvite.setIsValid(false);
            userInvitationRepository.save(userInvite);

            UserInvitationResponse userInvitationResponse = new UserInvitationResponse();
            userInvitationResponse.setEmail(userInvite.getEmail());
            userInvitationResponse.setRole(userInvite.getRole());
            userInvitationResponse.setValidUntil(userInvite.getValidUntil());
            userInvitationResponse.setIsValid(userInvite.getIsValid());
            return userInvitationResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserInviteEntity createInvitation(InviteUserRequest inviteUserRequest) {
        try {

            UserEntity userEntity = userService.getCurrentUserEntity();
            String email = inviteUserRequest.getEmail();
            UserType role = inviteUserRequest.getRole();

            if (!userEntity.getUserType().isAbsHigherRankThan(role)) {
                throw new NotPermittedException();
            }

            if (userService.userExists(email)) {
                throw new UserAlreadyExistsException();
            }

            UserInviteEntity userInviteEntity = new UserInviteEntity();
            userInviteEntity.setEmail(email);
            userInviteEntity.setRole(role);
            userInviteEntity.setIsValid(true);
            userInviteEntity.setInvitedAt(new Date());
            userInviteEntity.setValidUntil(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * VALIDITY_IN_DAYS)));
            userInviteEntity.setInviteCode(generateInviteCode());
            userInviteEntity.setInvitedBy(userEntity);

            return save(userInviteEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserInvitationResponse createInvitationResponse(InviteUserRequest inviteUserRequest) {
        try {
            UserInviteEntity userInviteEntity = createInvitation(inviteUserRequest);
            UserInvitationResponse userInvitationResponse = new UserInvitationResponse();
            userInvitationResponse.setEmail(userInviteEntity.getEmail());
            userInvitationResponse.setRole(userInviteEntity.getRole());
            userInvitationResponse.setValidUntil(userInviteEntity.getValidUntil());
            userInvitationResponse.setIsValid(userInviteEntity.getIsValid());
            emailService.sendUserInvitation(userInviteEntity.getEmail(), userInviteEntity);
            return userInvitationResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected UserInviteEntity getInvitation(String email, UserType role, String invitationCode) {
        try {

            Optional<UserInviteEntity> userInviteEntityOptional =
                    userInvitationRepository.findByEmailAndInviteCode(email, invitationCode);

            if (userInviteEntityOptional.isEmpty()) {
                throw new InvitationNotFoundException();
            }

            UserInviteEntity userInviteEntity = userInviteEntityOptional.get();

            if (!userInviteEntity.getRole().equals(role)) {
                throw new InvitationRoleMismatchException(userInviteEntity.getRole(), role);
            }

            if (!userInviteEntity.getIsValid()) {
                throw new InvitationIsNotValidException();
            }

            if (!userInviteEntity.getValidUntil().after(new Date())) {
                throw new InvitationIsExpiredException(userInviteEntity.getValidUntil());
            }

            return userInviteEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected UserInviteEntity save(UserInviteEntity userInviteEntity) {
        return userInvitationRepository.save(userInviteEntity);
    }

    private String generateInviteCode() {
        return UUID.randomUUID().toString();
    }
}
