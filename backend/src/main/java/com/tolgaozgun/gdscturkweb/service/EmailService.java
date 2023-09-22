package com.tolgaozgun.gdscturkweb.service;

import com.tolgaozgun.gdscturkweb.entity.EmailVerificationEntity;
import com.tolgaozgun.gdscturkweb.entity.UserInviteEntity;
import com.tolgaozgun.gdscturkweb.entity.user.UserEntity;
import com.tolgaozgun.gdscturkweb.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    protected boolean sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return true;
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Method 2
    // To send an email with attachment
    protected boolean sendMailWithAttachment(EmailDetails details) throws MessagingException {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return true;
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    protected boolean sendEmailVerification(UserEntity userEntity, EmailVerificationEntity emailVerificationEntity) {
        try {


            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(userEntity.getEmail());
            emailDetails.setSubject("GDSCTurk Email Verification");
            emailDetails.setMsgBody("Your verification code is: " + emailVerificationEntity.getVerificationCode());


            return sendSimpleMail(emailDetails);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected boolean sendUserInvitation(String email, UserInviteEntity userInviteEntity) {
        try {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(email);
            emailDetails.setSubject("GDSCTurk Invitation");
            emailDetails.setMsgBody("Your invitation code is: " + userInviteEntity.getInviteCode() +
                    "\nYou are invited to join GDSCTurk as " + userInviteEntity.getRole().name()
                    + "\nYou are invited by " + userInviteEntity.getInvitedBy().getName() + " " + userInviteEntity.getInvitedBy().getSurname()
                    + "\nPlease click the link below to register: ");

            return sendSimpleMail(emailDetails);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
