package com.cinema.controller;

import com.cinema.mapper.MovieSessionMapper;
import com.cinema.model.MovieSession;
import com.cinema.model.dto.session.MovieSessionRequestDto;
import com.cinema.model.dto.session.MovieSessionResponseDto;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper mapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper mapper) {
        this.movieSessionService = movieSessionService;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession = mapper.mapFromReqDtoToEntity(requestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getByParams(@RequestParam Long movieId, @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(mapper::mapToRespDto)
                .collect(Collectors.toList());
    }
}
