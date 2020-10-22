package com.cinema.controller;

import com.cinema.model.dto.UserAuthenticationDto;
import com.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserAuthenticationDto userAuthenticationDto) {
        service.register(userAuthenticationDto.getEmail(),
                userAuthenticationDto.getPassword());
    }
}
