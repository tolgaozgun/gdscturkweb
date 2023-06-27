package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.request.announcement.CreateAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.EditAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.announcement.GetAnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.Announcement;
import com.tolgaozgun.gdscturkweb.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "")
    public ResponseEntity<Object> getUserAnnouncements() {
        try {
            List<Announcement> announcements = announcementService.getAllAnnouncementsByUserType();
            return Response.create("Gathered all announcements", HttpStatus.OK, announcements);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "all")
    public ResponseEntity<Object> getAllAnnouncements() {
        try {
            List<Announcement> announcements = announcementService.getAllAnnouncements();
            return Response.create("Gathered all announcements", HttpStatus.OK, announcements);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-id")
    public ResponseEntity<Object> getAnnouncementById(@Valid @RequestBody GetAnnouncementRequest getAnnouncementRequest) {
        try {
            Announcement announcement = announcementService.getAnnouncementById(getAnnouncementRequest);
            return Response.create("Found the announcement", HttpStatus.OK, announcement);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<Object> createAnnouncement(@Valid @RequestBody CreateAnnouncementRequest announcementRequest) {
        try {
            Announcement announcement = announcementService.createAnnouncement(announcementRequest);
            return Response.create("Announcement created successfully", HttpStatus.OK, announcement);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "edit")
    public ResponseEntity<Object> editAnnouncement(@Valid @RequestBody EditAnnouncementRequest editAnnouncementRequest) {
        try {
            Announcement announcement = announcementService.editAnnouncement(editAnnouncementRequest);
            return Response.create("Announcement edited successfully", HttpStatus.OK, announcement);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
