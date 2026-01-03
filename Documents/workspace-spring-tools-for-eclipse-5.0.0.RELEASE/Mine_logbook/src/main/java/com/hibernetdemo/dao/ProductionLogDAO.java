package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.ProductionLog;
import com.hibernetdemo.util.HibernateUtil;

public class ProductionLogDAO {

    public void save(ProductionLog log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(log);

        tx.commit();
        session.close();
    }
}
