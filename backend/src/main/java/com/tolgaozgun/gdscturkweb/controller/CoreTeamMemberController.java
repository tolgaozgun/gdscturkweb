package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.CoreTeamMemberDTO;
import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.FindCoreTeamMemberByUniversityRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.service.CoreTeamMemberService;
import com.tolgaozgun.gdscturkweb.service.LeadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/core-team-members")
public class CoreTeamMemberController {

    private final CoreTeamMemberService coreTeamMemberService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getAllCoreTeamMembers() {
        try {
            List<CoreTeamMemberDTO> coreTeamMembers = coreTeamMemberService.getAllCoreTeamMembers();
            return Response.create("Gathered all core team members", HttpStatus.OK, coreTeamMembers);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-university")
    public ResponseEntity<Object> getAllCoreTeamMembersByUniversity(
            @Valid @RequestBody FindCoreTeamMemberByUniversityRequest findCoreTeamMemberByUniversityRequest) {
        try {
            List<CoreTeamMemberDTO> coreTeamMembers = coreTeamMemberService.getAllCoreTeamMembersByUniversity(findCoreTeamMemberByUniversityRequest);
            return Response.create("Gathered all core team members", HttpStatus.OK, coreTeamMembers);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
