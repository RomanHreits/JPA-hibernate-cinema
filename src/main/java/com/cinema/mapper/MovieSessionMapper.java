package com.cinema.mapper;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;

public interface MovieSessionMapper extends GenericMapper<MovieSession,
        MovieSessionRequestDto, MovieSessionResponseDto> {
}
