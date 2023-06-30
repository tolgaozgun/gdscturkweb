package com.tolgaozgun.gdscturkweb.controller;


import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.FacilitatorDTO;
import com.tolgaozgun.gdscturkweb.dto.GooglerDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.profile.*;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.user.Googler;
import com.tolgaozgun.gdscturkweb.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update/lead")
    public ResponseEntity<Object> updateLeadProfileByLead(@Valid @RequestBody UpdateLeadProfileByLeadRequest
                                                                      updateLeadProfileByLeadRequest) {
        try {
            LeadDTO leadDTO = profileService.updateLeadProfile(updateLeadProfileByLeadRequest);
            return Response.create("Update is successful", HttpStatus.OK, leadDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update/core-team")
    public ResponseEntity<Object> updateCoreTeamProfileByCore(@Valid @RequestBody UpdateCoreTeamMemberProfileByMemberRequest
                                                                  updateCoreTeamMemberProfileByMemberRequest) {
        try {
            CoreTeamMemberDTO coreTeamMemberDTO = profileService.updateCoreTeamMemberProfile(updateCoreTeamMemberProfileByMemberRequest);
            return Response.create("Update is successful", HttpStatus.OK, coreTeamMemberDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update/facilitator")
    public ResponseEntity<Object> updateFacilitatorProfile(@Valid @RequestBody UpdateFacilitatorProfileByFacilitatorRequest
                                                                   updateFacilitatorProfileByFacilitatorRequest) {
        try {
            FacilitatorDTO facilitatorDTO = profileService.updateFacilitatorProfile(updateFacilitatorProfileByFacilitatorRequest);
            return Response.create("Update is successful", HttpStatus.OK, facilitatorDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update/googler")
    public ResponseEntity<Object> updateGooglerProfile(@Valid @RequestBody UpdateGooglerProfileByGooglerRequest
                                                                   updateGooglerProfileByGooglerRequest) {
        try {
            GooglerDTO googlerDTO = profileService.updateGooglerProfile(updateGooglerProfileByGooglerRequest);
            return Response.create("Update is successful", HttpStatus.OK, googlerDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update-staff/lead")
    public ResponseEntity<Object> updateLeadProfileByStaff(@Valid @RequestBody UpdateLeadProfileByStaffRequest
                                                                  updateLeadProfileByStaffRequest) {
        try {
            LeadDTO leadDTO = profileService.updateLeadProfileByStaff(updateLeadProfileByStaffRequest);
            return Response.create("Update is successful", HttpStatus.OK, leadDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update-staff/core-team")
    public ResponseEntity<Object> updateCoreTeamProfileByStaff(@Valid @RequestBody UpdateCoreTeamMemberProfileByStaffRequest
                                                                      updateCoreTeamMemberProfileByStaffRequest) {
        try {
            CoreTeamMemberDTO coreTeamMemberDTO = profileService.updateCoreTeamMemberProfileByStaff(updateCoreTeamMemberProfileByStaffRequest);
            return Response.create("Update is successful", HttpStatus.OK, coreTeamMemberDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update-staff/facilitator")
    public ResponseEntity<Object> updateFacilitatorProfileByStaff(@Valid @RequestBody UpdateFacilitatorProfileByStaffRequest
                                                                   updateFacilitatorProfileByStaffRequest) {
        try {
            FacilitatorDTO facilitatorDTO = profileService.updateFacilitatorProfileByStaff(updateFacilitatorProfileByStaffRequest);
            return Response.create("Update is successful", HttpStatus.OK, facilitatorDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "update-staff/googler")
    public ResponseEntity<Object> updateGooglerProfileByStaff(@Valid @RequestBody UpdateGooglerProfileByStaffRequest
                                                                  updateGooglerProfileByStaffRequest) {
        try {
            GooglerDTO googlerDTO = profileService.updateGooglerProfileByStaff(updateGooglerProfileByStaffRequest);
            return Response.create("Update is successful", HttpStatus.OK, googlerDTO);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
