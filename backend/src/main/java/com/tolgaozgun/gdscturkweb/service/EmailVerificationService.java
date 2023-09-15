package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.dto.request.verification.EmailResendRequest;
import com.tolgaozgun.gdscturkweb.dto.request.verification.EmailVerificationRequest;
import com.tolgaozgun.gdscturkweb.entity.EmailVerificationEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.exception.EmailVerificationNotFoundException;
import com.tolgaozgun.gdscturkweb.exception.verification.EmailAlreadyVerifiedException;
import com.tolgaozgun.gdscturkweb.exception.verification.EmailVerificationAlreadyUsedException;
import com.tolgaozgun.gdscturkweb.exception.verification.EmailVerificationExpiredException;
import com.tolgaozgun.gdscturkweb.mapper.EmailVerificationMapper;
import com.tolgaozgun.gdscturkweb.model.EmailVerification;
import com.tolgaozgun.gdscturkweb.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailVerificationMapper emailVerificationMapper;

    private final UserService userService;
    private final EmailService emailService;


    protected boolean sendVerificationCodeWithEntity(UserEntity userEntity){
        try {
            EmailVerificationEntity emailVerificationEntity = createVerificationCodeWithEntity(userEntity);
            return emailService.sendEmailVerification(userEntity, emailVerificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected EmailVerificationEntity createVerificationCodeWithEntity(UserEntity userEntity){
        try {
            Optional<EmailVerificationEntity> optionalEmailVerificationEntity =
                    emailVerificationRepository.findByUser(userEntity);

            if (optionalEmailVerificationEntity.isPresent()) {
                EmailVerificationEntity emailVerificationEntity = optionalEmailVerificationEntity.get();
                emailVerificationEntity.setIsUsed(false);
                Date date = new Date();
                Date minuteAgo = new Date(date.getTime() - (1000 * 60));
                emailVerificationEntity.setValidUntil(minuteAgo);
                return emailVerificationRepository.save(emailVerificationEntity);
            }

            EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(userEntity);
            return emailVerificationRepository.save(emailVerificationEntity);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected EmailVerification createVerificationCodeWithModel(UserEntity userEntity) {
        try {
            EmailVerificationEntity emailVerificationEntity = createVerificationCodeWithEntity(userEntity);
            return emailVerificationMapper.toModel(emailVerificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String resendVerification(EmailResendRequest emailResendRequest) {
        try {
            String email = emailResendRequest.getEmail();
            return resendVerification(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String resendVerification(String email) {
        try {
            UserEntity userEntity = userService.getUserEntityFromEmail(email);

            if (userEntity.getIsEmailVerified()) {
                throw new EmailAlreadyVerifiedException();
            }

            EmailVerificationEntity emailVerificationEntity = createVerificationCodeWithEntity(userEntity);
            emailService.sendEmailVerification(userEntity, emailVerificationEntity);
            return email;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String verifyEmail(EmailVerificationRequest emailVerificationRequest) {
        try {
            String email = emailVerificationRequest.getEmail();
            String verificationCode = emailVerificationRequest.getVerificationCode();
            return verifyEmail(email, verificationCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String verifyEmail(String email, String verificationCode) {
        try {
            UserEntity userEntity = userService.getUserEntityFromEmail(email);
            Optional<EmailVerificationEntity> optEmailVerificationEntity =
                    emailVerificationRepository.findByUserAndVerificationCode(userEntity, verificationCode);
            if (optEmailVerificationEntity.isEmpty()) {
                throw new EmailVerificationNotFoundException();
            }

            EmailVerificationEntity emailVerificationEntity = optEmailVerificationEntity.get();

            if (emailVerificationEntity.getValidUntil() == null ||
                    emailVerificationEntity.getValidUntil().after(new Date())) {
                Date expiredDate = emailVerificationEntity.getValidUntil();
                if (expiredDate == null)
                    throw new EmailVerificationExpiredException();
                else
                    throw new EmailVerificationExpiredException(emailVerificationEntity.getValidUntil());
            }

            if (emailVerificationEntity.getIsUsed()) {
                throw new EmailVerificationAlreadyUsedException();
            }

            emailVerificationEntity.setIsUsed(true);
            emailVerificationRepository.save(emailVerificationEntity);

            userEntity.setIsEmailVerified(true);
            userService.save(userEntity);

            return email;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
