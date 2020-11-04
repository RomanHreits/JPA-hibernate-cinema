package com.cinema.dao.impl;

import com.cinema.dao.RoleDao;
import com.cinema.exceptions.DataProcessingException;
import com.cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add to DB role: " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role get(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Role where roleName = :role", Role.class)
                    .setParameter("role", Role.RoleType.valueOf(roleName))
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Role by roleName: " + roleName, e);
        }
    }
}
