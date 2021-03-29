package com.cinema.model.dto.user;

public class AuthenticationResponseDto {
    private final String jwtToken;

    public AuthenticationResponseDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
