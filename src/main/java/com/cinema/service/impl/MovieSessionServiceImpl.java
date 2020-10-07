package com.cinema.service.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.MovieSession;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao sessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return sessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return sessionDao.add(session);
    }
}
