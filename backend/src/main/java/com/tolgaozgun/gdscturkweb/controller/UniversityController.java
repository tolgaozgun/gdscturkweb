package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.request.city.CreateCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.EditCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.GetCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.university.CreateUniversityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.university.EditUniversityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.university.GetUniversityRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.service.UniversityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/universities")
public class UniversityController {

    private final UniversityService universityService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getAllUniversities() {
        try {
            List<University> universityList = universityService.getAllUniversities();
            return Response.create("Gathered all universities", HttpStatus.OK, universityList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-id")
    public ResponseEntity<Object> getUniversityById(@Valid @RequestBody GetUniversityRequest getUniversityRequest) {
        try {
            University university = universityService.getUniversityById(getUniversityRequest);
            return Response.create("Found the university", HttpStatus.OK, university);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<Object> createCity(@Valid @RequestBody CreateUniversityRequest createUniversityRequest) {
        try {
            University university = universityService.createUniversity(createUniversityRequest);
            return Response.create("University created successfully", HttpStatus.OK, university);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "edit")
    public ResponseEntity<Object> editCity(@Valid @RequestBody EditUniversityRequest editUniversityRequest) {
        try {
            University university = universityService.editUniversity(editUniversityRequest);
            return Response.create("University edited successfully", HttpStatus.OK, university);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
