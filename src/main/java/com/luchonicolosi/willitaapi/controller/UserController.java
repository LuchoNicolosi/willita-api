package com.luchonicolosi.willitaapi.controller;

import com.luchonicolosi.willitaapi.dto.Auth.AuthenticateResponse;
import com.luchonicolosi.willitaapi.dto.Auth.LoginDto;
import com.luchonicolosi.willitaapi.dto.Auth.SignUpDto;
import com.luchonicolosi.willitaapi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;
    private final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true) SignUpDto data) {
        try {
            userService.signup(data);
            return new ResponseEntity<>("User register success!", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error creating an user - " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody(required = true) LoginDto data) throws Exception {
        try {
            String token = userService.login(data);
            if (token != null) return new ResponseEntity<>(new AuthenticateResponse(token), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Failed to authenticate -> " + e);
        }
        return ResponseEntity.internalServerError().body("Something went wrong");
    }

    @PostMapping("/admin/login")
    public ResponseEntity adminLogin(@RequestBody(required = true) LoginDto data) throws Exception {
        LOGGER.info("En el controller admin/login");
        try {
            String token = userService.login(data);
            if (token != null) return new ResponseEntity<>(new AuthenticateResponse(token), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Failed to authenticate -> " + e);
        }
        return ResponseEntity.internalServerError().body("Something went wrong");
    }
}
