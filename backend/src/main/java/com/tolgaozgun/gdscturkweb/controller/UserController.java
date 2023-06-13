package com.tolgaozgun.gdscturkweb.controller;

import com.tolgaozgun.gdscturkweb.dto.LoginDTO;
import com.tolgaozgun.gdscturkweb.dto.LoginTokenDTO;
import com.tolgaozgun.gdscturkweb.exception.ExceptionLogger;
import com.tolgaozgun.gdscturkweb.response.Response;
import com.tolgaozgun.gdscturkweb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class UserController {


    private final UserService userService;


    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            LoginTokenDTO token = userService.login(loginDTO);
            return Response.create("login is successful", HttpStatus.OK, token);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
