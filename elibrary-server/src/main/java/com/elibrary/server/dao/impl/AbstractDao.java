package com.elibrary.server.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Save entity object into database
     *
     * @param entity
     */
    protected void save(Object entity) {
        getSession().save(entity);
    }

    /**
     * Delete entity object from database
     *
     * @param entity
     */
    protected void delete(Object entity) {
        getSession().delete(entity);
    }

    /**
     * Update entity object into database
     *
     * @param entity
     */
    protected void update(Object entity) {
        getSession().update(entity);
    }
}
