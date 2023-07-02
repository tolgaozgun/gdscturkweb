package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.request.city.CreateCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.EditCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.city.GetCityRequest;
import com.tolgaozgun.gdscturkweb.dto.request.permission.GetPermissionsByUserIdRequest;
import com.tolgaozgun.gdscturkweb.dto.response.Response;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.model.City;
import com.tolgaozgun.gdscturkweb.model.Permission;
import com.tolgaozgun.gdscturkweb.service.CityService;
import com.tolgaozgun.gdscturkweb.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping( path = "")
    public ResponseEntity<Object> getCurrentUserPermissions() {
        try {
            List<Permission> permissions = permissionService.getCurrentUserPermissions();
            return Response.create("Gathered all permissions for the user", HttpStatus.OK, permissions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "by-id")
    public ResponseEntity<Object> getCityById(@Valid @RequestBody GetPermissionsByUserIdRequest getPermissionsByUserIdRequest) {
        try {
            List<Permission> permissions = permissionService.getUserPermissions(getPermissionsByUserIdRequest);
            return Response.create("Gathered all permissions for the user", HttpStatus.OK, permissions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
