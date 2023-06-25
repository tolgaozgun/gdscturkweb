package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.LoginRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LoginResponse;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse token = authService.login(loginRequest);
            return Response.create("login is successful", HttpStatus.OK, token);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/lead")
    public ResponseEntity<Object> registerLead(@Valid @RequestBody LeadRegisterRequest leadRegisterRequest) {
        try {
            LeadDTO leadDTO = authService.registerLead(leadRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, leadDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/coreteam")
    public ResponseEntity<Object> registerLead(@Valid @RequestBody CoreTeamRegisterRequest coreTeamRegisterRequest) {
        try {
            CoreTeamMember coreTeamMember = authService.registerCoreTeam(coreTeamRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, coreTeamMember);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
