package com.springmvc.demo.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HibernateSessionFactory {

    private SessionFactory sessionFactory;

    @PostConstruct
    private void init() {
        sessionFactory = new Configuration()
                .configure("config/ hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @PreDestroy
    private void shutdown() {
        sessionFactory.close();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
