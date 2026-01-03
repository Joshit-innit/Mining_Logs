package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.MineSite;
import com.hibernetdemo.util.HibernateUtil;

public class MineSiteDAO {

    public void save(MineSite mine) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(mine);

        tx.commit();
        session.close();
    }

    public MineSite findById(int mineId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        MineSite mine = session.get(MineSite.class, mineId);
        session.close();
        return mine;
    }

    public void updateStatus(int mineId, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        MineSite mine = session.get(MineSite.class, mineId);
        if (mine != null) {
            mine.setStatus(status);
        }

        tx.commit();
        session.close();
    }
}
