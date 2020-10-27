package com.cinema.controller;

import com.cinema.model.dto.user.UserResponseDto;
import com.cinema.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ModelMapper mapper;
    private final UserService userService;

    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto get(@RequestParam String email) {
        return mapper.map(userService.findByEmail(email), UserResponseDto.class);
    }
}
