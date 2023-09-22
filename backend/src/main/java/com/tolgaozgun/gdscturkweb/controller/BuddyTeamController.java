package com.tolgaozgun.gdscturkweb.controller;


import com.tolgaozgun.gdscturkweb.dto.BuddyTeamDTO;
import com.tolgaozgun.gdscturkweb.dto.request.buddyTeam.UpdateBuddyTeamRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.service.BuddyTeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/buddy-teams")
public class BuddyTeamController {

    private final BuddyTeamService buddyTeamService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "")
    public ResponseEntity<Object> getAllBuddyTeams() {
        try {
            List<BuddyTeamDTO> buddyTeams = buddyTeamService.getAllBuddyTeams();
            return Response.create("Gathered all buddy teams", HttpStatus.OK, buddyTeams);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "by-lead")
    public ResponseEntity<Object> getBuddyTeamOfCurrentUser() {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.getBuddyTeamDTOByCurrentUser();
            return Response.create("Gathered the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "by-facilitator")
    public ResponseEntity<Object> getBuddyTeamOfFacilitator() {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.getBuddyTeamDTOByFacilitator();
            return Response.create("Gathered the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(path = "update/{buddyTeamId}")
    public ResponseEntity<Object> updateBuddyTeamByBuddyTeamId(@PathVariable Long buddyTeamId,
                                                               @RequestBody UpdateBuddyTeamRequest updateBuddyTeamRequest) {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.updateBuddyTeamById(buddyTeamId, updateBuddyTeamRequest);
            return Response.create("Updated the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(path = "update/by-facilitator")
    public ResponseEntity<Object> updateBuddyTeamByFacilitator(@RequestBody UpdateBuddyTeamRequest updateBuddyTeamRequest) {
        try {
            System.out.println(updateBuddyTeamRequest);
            BuddyTeamDTO buddyTeam = buddyTeamService.updateBuddyTeamByFacilitator(updateBuddyTeamRequest);
            return Response.create("Updated the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(path = "update/by-lead")
    public ResponseEntity<Object> updateBuddyTeamByLead(@RequestBody UpdateBuddyTeamRequest updateBuddyTeamRequest) {
        try {
            BuddyTeamDTO buddyTeam = buddyTeamService.updateBuddyTeamByLead(updateBuddyTeamRequest);
            return Response.create("Updated the buddy team", HttpStatus.OK, buddyTeam);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

}
