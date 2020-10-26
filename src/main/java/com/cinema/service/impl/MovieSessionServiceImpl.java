package com.cinema.service.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.model.MovieSession;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao sessionDao;

    public MovieSessionServiceImpl(MovieSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return sessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return sessionDao.add(session);
    }

    @Override
    public MovieSession findById(Long id) {
        return sessionDao.findById(id);
    }
}
