package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.*;
import com.tolgaozgun.gdscturkweb.dto.request.LoginRequest;
import com.tolgaozgun.gdscturkweb.dto.request.VerifyUserRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.CoreTeamRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.FacilitatorRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.GooglerRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LoginResponse;
import com.tolgaozgun.gdscturkweb.dto.response.UserWithRoleResponse;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.model.user.CoreTeamMember;
import com.tolgaozgun.gdscturkweb.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private final CoreTeamMemberService coreTeamService;
    private final LeadService leadService;
    private final GooglerService googlerService;
    private final FacilitatorService facilitatorService;


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse token = authService.login(loginRequest);
            return Response.create("login is successful", HttpStatus.OK, token);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


//    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "logout")
//    public ResponseEntity<Object> logout() {
//        try {
//            LoginResponse token = authService.logout();
//            return Response.create("login is successful", HttpStatus.OK, token);
//        } catch (Exception e) {
//            // HTTP 500
//            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
//    }

//
//    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
//    @PostMapping(path = "refresh")
//    public void refreshPost() {}
//    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
//    @GetMapping(path = "refresh")
//    public void refreshGet() {}

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/lead")
    public ResponseEntity<Object> registerLead(@Valid @RequestBody LeadRegisterRequest leadRegisterRequest) {
        try {
            LeadDTO leadDTO = leadService.registerLead(leadRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, leadDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/core-team")
    public ResponseEntity<Object> registerCoreTeam(@Valid @RequestBody CoreTeamRegisterRequest coreTeamRegisterRequest) {
        try {
            CoreTeamMemberDTO coreTeamMember = coreTeamService.registerCoreTeam(coreTeamRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, coreTeamMember);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/googler")
    public ResponseEntity<Object> registerGoogler(@Valid @RequestBody GooglerRegisterRequest googlerRegisterRequest) {
        try {
            GooglerDTO googlerDTO = googlerService.registerGoogler(googlerRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, googlerDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/facilitator")
    public ResponseEntity<Object> registerFacilitator(@Valid @RequestBody FacilitatorRegisterRequest facilitatorRegisterRequest) {
        try {
            FacilitatorDTO facilitatorDTO = facilitatorService.registerFacilitator(facilitatorRegisterRequest);
            return Response.create("Register is successful", HttpStatus.OK, facilitatorDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "verify-list")
    public ResponseEntity<Object> getVerifyList() {
        try {
            List<UserDTO> verifyList = authService.getVerifyList();
            return Response.create("Gathered verification list", HttpStatus.OK, verifyList);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "verify/{userId}")
    public ResponseEntity<Object> verifyUser(@PathVariable Long userId) {
        try {
            UserDTO userDTO = authService.verifyUser(userId);
            return Response.create("Verification is successful", HttpStatus.OK, userDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "unverify/{userId}")
    public ResponseEntity<Object> unverifyUser(@PathVariable Long userId) {
        try {
            UserDTO userDTO = authService.unverifyUser(userId);
            return Response.create("Unverification is successful", HttpStatus.OK, userDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "blacklist/{userId}")
    public ResponseEntity<Object> blackListUser(@PathVariable Long userId) {
        try {
            UserDTO userDTO = authService.blackListUser(userId);
            return Response.create("Unverification is successful", HttpStatus.OK, userDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "unblacklist/{userId}")
    public ResponseEntity<Object> unBlackListUser(@PathVariable Long userId) {
        try {
            UserDTO userDTO = authService.unBlackListUser(userId);
            return Response.create("Unverification is successful", HttpStatus.OK, userDTO);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "current-user")
    public ResponseEntity<Object> getCurrentUser() {
        try {
            UserDTO currentUser = authService.getCurrentUser();
            return Response.create("Gathered the current user", HttpStatus.OK, currentUser);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "current-user-with-role")
    public ResponseEntity<Object> getCurrentUserWithRole() {
        try {
            UserWithRoleResponse userWithRoleResponse = authService.getCurrentUserWithRole();
            return Response.create("Gathered the current user with role", HttpStatus.OK, userWithRoleResponse);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }




}
