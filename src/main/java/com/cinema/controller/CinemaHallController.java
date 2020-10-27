package com.cinema.controller;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.hall.CinemaHallRequestDto;
import com.cinema.model.dto.hall.CinemaHallResponseDto;
import com.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final ModelMapper mapper;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService, ModelMapper mapper) {
        this.cinemaHallService = cinemaHallService;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(mapper.map(cinemaHallRequestDto, CinemaHall.class));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHall -> mapper.map(cinemaHall, CinemaHallResponseDto.class))
                .collect(Collectors.toList());
    }
}
