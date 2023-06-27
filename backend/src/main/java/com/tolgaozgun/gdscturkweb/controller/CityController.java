package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.LeadDTO;
import com.tolgaozgun.gdscturkweb.dto.request.AnnouncementRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.CreateCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.EditCityRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.Announcement;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.service.CityService;
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
@RequestMapping("api/v1/cities")
public class CityController {

    private final CityService cityService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getAllCities() {
        try {
            List<City> cityList = cityService.getAllCities();
            return Response.create("Gathered all cities", HttpStatus.OK, cityList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<Object> createCity(@Valid @RequestBody CreateCityRequest createCityRequest) {
        try {
            City city = cityService.createCity(createCityRequest);
            return Response.create("City created successfully", HttpStatus.OK, city);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "edit")
    public ResponseEntity<Object> editCity(@Valid @RequestBody EditCityRequest editCityRequest) {
        try {
            City city = cityService.editCity(editCityRequest);
            return Response.create("City edited successfully", HttpStatus.OK, city);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}