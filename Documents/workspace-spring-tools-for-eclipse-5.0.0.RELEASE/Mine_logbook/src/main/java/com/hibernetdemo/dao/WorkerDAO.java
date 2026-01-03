package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.Worker;
import com.hibernetdemo.util.HibernateUtil;

public class WorkerDAO {

    public void save(Worker worker) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(worker);

        tx.commit();
        session.close();
    }

    public Worker findById(int workerId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Worker w = session.get(Worker.class, workerId);
        session.close();
        return w;
    }
}
