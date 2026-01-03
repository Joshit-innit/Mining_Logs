package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.EquipmentUsage;
import com.hibernetdemo.util.HibernateUtil;

public class EquipmentUsageDAO {

    public void save(EquipmentUsage usage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(usage);

        tx.commit();
        session.close();
    }
}
