package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exceptions.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery("from MovieSession ms "
                    + "join fetch ms.movie "
                    + "join fetch ms.cinemaHall where ms.movie.id = :id "
                    + "and ms.showTime between :startDay and :endDay", MovieSession.class);
            query.setParameter("id", movieId);
            query.setParameter("startDay", date.atTime(LocalTime.MIN));
            query.setParameter("endDay", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions!", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = hibernateSession.beginTransaction();
            hibernateSession.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add MovieSession to DB", e);
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }
}
