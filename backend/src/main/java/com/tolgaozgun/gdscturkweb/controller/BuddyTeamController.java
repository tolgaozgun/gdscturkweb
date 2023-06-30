package com.tolgaozgun.gdscturkweb.controller;


import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.request.BuddyTeamByFacilitatorRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.BuddyTeam;
import com.tolgaozgun.gdscturkweb.service.BuddyTeamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/buddy-teams")
public class BuddyTeamController {

    private final BuddyTeamService buddyTeamService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "by-me")
    public ResponseEntity<Object> getBuddyTeamOfCurrentUser() {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.getBuddyTeamByCurrentUser();
            return Response.create("Gathered the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-facilitator")
    public ResponseEntity<Object> getBuddyTeamOfFacilitator(@Valid @RequestBody BuddyTeamByFacilitatorRequest
                                                                        buddyTeamByFacilitatorRequest) {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.getBuddyTeamByFacilitator(buddyTeamByFacilitatorRequest);
            return Response.create("Gathered the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
