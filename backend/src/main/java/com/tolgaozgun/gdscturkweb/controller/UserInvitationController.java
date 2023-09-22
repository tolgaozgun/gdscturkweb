package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.request.invitation.InviteUserRequest;
import com.tolgaozgun.gdscturkweb.dto.request.verification.EmailResendRequest;
import com.tolgaozgun.gdscturkweb.dto.request.verification.EmailVerificationRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.dto.response.UserInvitationResponse;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.service.EmailVerificationService;
import com.tolgaozgun.gdscturkweb.service.UserInvitationService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/invitation")
public class UserInvitationController {

    private final UserInvitationService userInvitationService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping( path = "invite")
    public ResponseEntity<Object> inviteUser(@RequestBody @NotNull InviteUserRequest inviteUserRequest) {
        try {
            System.out.println("inviteUserRequest: " + inviteUserRequest);
            UserInvitationResponse response = userInvitationService.createInvitationResponse(inviteUserRequest);
            return Response.create("Successfully invited user", HttpStatus.OK, response);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping( path = "cancel/{invitationId}")
    public ResponseEntity<Object> cancelInvitation(@PathVariable Long invitationId) {
        try {
            UserInvitationResponse response = userInvitationService.cancelInvitation(invitationId);
            return Response.create("Successfully cancelled the invite", HttpStatus.OK, response);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }



}
