package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.Equipment;
import com.hibernetdemo.util.HibernateUtil;

public class EquipmentDAO {

    public void save(Equipment equipment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(equipment);

        tx.commit();
        session.close();
    }

    public Equipment findById(int equipmentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Equipment e = session.get(Equipment.class, equipmentId);
        session.close();
        return e;
    }

    public void updateMine(int equipmentId, int newMineId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Equipment e = session.get(Equipment.class, equipmentId);
        if (e != null) {
            e.setMineId(newMineId);
        }

        tx.commit();
        session.close();
    }
}
