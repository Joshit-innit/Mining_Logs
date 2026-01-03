package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.ShiftLog;
import com.hibernetdemo.util.HibernateUtil;

public class ShiftLogDAO {

    public void save(ShiftLog shift) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(shift);

        tx.commit();
        session.close();
    }

    public ShiftLog findById(int shiftId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ShiftLog s = session.get(ShiftLog.class, shiftId);
        session.close();
        return s;
    }
}
