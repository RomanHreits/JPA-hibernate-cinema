package com.cinema.controller;

import com.cinema.model.dto.user.UserRegistrationDto;
import com.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        authService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}
