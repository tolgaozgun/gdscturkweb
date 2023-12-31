package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.register.LeadRegisterRequest;
import com.tolgaozgun.gdscturkweb.dto.response.LeadDashboardResponse;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.service.AuthService;
import com.tolgaozgun.gdscturkweb.service.LeadService;
import com.tolgaozgun.gdscturkweb.service.UniversityService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/leads")
public class LeadController {

    private final LeadService leadService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getAllLeads() {
        try {
            List<LeadDTO> leadList = leadService.getAllLeads();
            return Response.create("Gathered all leads", HttpStatus.OK, leadList);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping( path = "create")
    public ResponseEntity<Object> createLead(@NotNull @RequestBody LeadRegisterRequest leadRegisterRequest) {
        try {
            LeadDTO lead = leadService.registerLead(leadRegisterRequest);
            return Response.create("Created lead", HttpStatus.OK, lead);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "dashboard")
    public ResponseEntity<Object> getCurrentLeadDashboard() {
        try {
            LeadDashboardResponse leadDashboardResponse = leadService.getCurrentLeadDashboard();
            return Response.create("Gathered current lead dashboard", HttpStatus.OK, leadDashboardResponse);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }




}
