package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.University;
import com.tolgaozgun.gdscturkweb.service.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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


}
