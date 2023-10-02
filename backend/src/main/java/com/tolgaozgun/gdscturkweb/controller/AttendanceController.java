package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.AnnouncementDTO;
import com.tolgaozgun.gdscturkweb.dto.AttendanceDTO;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.CreateAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.EditAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.attendance.CreateAttendanceRequest;
import com.tolgaozgun.gdscturkweb.dto.request.attendance.EditAttendanceRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.LeadAttendance;
import com.tolgaozgun.gdscturkweb.service.AnnouncementService;
import com.tolgaozgun.gdscturkweb.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "")
    public ResponseEntity<Object> getAllAttendances() {
        try {
            List<AttendanceDTO> announcements = attendanceService.getAllAttendances();
            return Response.create("Gathered all attendances", HttpStatus.OK, announcements);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "lead/current")
    public ResponseEntity<Object> getLeadAttendances() {
        try {
            List<LeadAttendance> announcements = attendanceService.getAllAttendancesOfCurrentUser();
            return Response.create("Gathered all lead attendances for current user", HttpStatus.OK, announcements);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "lead/{leadId}")
    public ResponseEntity<Object> getLeadAttendances(@PathVariable Long leadId) {
        try {
            List<LeadAttendance> announcements = attendanceService.getAllAttendancesOfLead(leadId);
            return Response.create("Gathered all lead attendances for lead with ID " + leadId, HttpStatus.OK, announcements);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "facilitator/{facilitatorId}")
    public ResponseEntity<Object> getFacilitatorBuddyTeamAttendance(@PathVariable Long facilitatorId) {
        try {
            List<AttendanceDTO> attendances = attendanceService.getAllAttendancesOfFacilitator(facilitatorId);
            return Response.create("Gathered all buddy team attendances for facilitator with ID " + facilitatorId, HttpStatus.OK, attendances);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getAttendanceById(@PathVariable Long id) {
        try {
            AttendanceDTO announcement = attendanceService.getAttendanceById(id);
            return Response.create("Found the attendance", HttpStatus.OK, announcement);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<Object> createAttendance(@Valid @RequestBody CreateAttendanceRequest createAttendanceRequest) {
        try {
            AttendanceDTO announcement = attendanceService.createAttendance(createAttendanceRequest);
            return Response.create("Attendance created successfully", HttpStatus.OK, announcement);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "edit/{id}")
    public ResponseEntity<Object> editAttendance(@PathVariable Long id, @Valid @RequestBody EditAttendanceRequest editAttendanceRequest) {
        try {
            AttendanceDTO announcement = attendanceService.editAttendance(id, editAttendanceRequest);
            return Response.create("Attendance edited successfully", HttpStatus.OK, announcement);
        } catch (Exception e) {
            // HTTP 500
            return Response.create(ExceptionLogger.log(e), HttpStatus.OK);        }
    }
}
