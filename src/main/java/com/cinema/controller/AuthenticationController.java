package com.cinema.controller;

import com.cinema.model.dto.UserAuthenticationDto;
import com.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authentication;

    public AuthenticationController(AuthenticationService authentication) {
        this.authentication = authentication;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserAuthenticationDto userAuthenticationDto) {
        authentication.register(userAuthenticationDto.getEmail(),
                userAuthenticationDto.getPassword());
    }
}
