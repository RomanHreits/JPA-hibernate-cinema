package com.cinema.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapperImpl implements MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapperImpl(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSession mapFromReqDtoToEntity(MovieSessionRequestDto dto) {
        CinemaHall cinemaHall = cinemaHallService.getById(dto.getCinemaHallId());
        Movie movie = movieService.getById(dto.getMovieId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.parse(dto.getShowTime()));
        return movieSession;
    }

    @Override
    public MovieSessionRequestDto mapToReqDto(MovieSession entity) {
        MovieSessionRequestDto requestDto = new MovieSessionRequestDto();
        requestDto.setCinemaHallId(entity.getCinemaHall().getId());
        requestDto.setMovieId(entity.getMovie().getId());
        requestDto.setShowTime(entity.getShowTime().toString());
        requestDto.setMovieSessionId(entity.getId());
        return requestDto;
    }

    @Override
    public MovieSessionResponseDto mapToRespDto(MovieSession entity) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setCinemaHallId(entity.getCinemaHall().getId());
        responseDto.setMovieTitle(entity.getMovie().getTitle());
        responseDto.setShowTime(entity.getShowTime().toString());
        responseDto.setMovieSessionId(entity.getId());
        return responseDto;
    }
}
