package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exceptions.DataProcessingException;
import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = LogManager.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("from MovieSession ms "
                    + "join fetch ms.movie "
                    + "join fetch ms.cinemaHall where ms.movie.id = :id "
                    + "and ms.showTime between :startDay and :endDay", MovieSession.class);
            query.setParameter("id", movieId);
            query.setParameter("startDay", date.atTime(LocalTime.MIN));
            query.setParameter("endDay", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions with movieId: "
                    + movieId + " and date " + date.toString(), e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session hibernateSession = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = hibernateSession.beginTransaction();
            hibernateSession.persist(movieSession);
            transaction.commit();
            logger.info("Object MovieSession was added to DB! " + movieSession);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add to DB MovieSession: "
                    + movieSession, e);
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }

    @Override
    public MovieSession get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find MovieSession with id: " + id, e);
        }
    }
}
