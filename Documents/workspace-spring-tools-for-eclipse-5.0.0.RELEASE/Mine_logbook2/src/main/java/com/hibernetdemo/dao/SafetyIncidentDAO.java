package com.hibernetdemo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernetdemo.entity.SafetyIncident;
import com.hibernetdemo.util.HibernateUtil;

public class SafetyIncidentDAO {

    public void save(SafetyIncident incident) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(incident);

        tx.commit();
        session.close();
    }

    public void closeIncident(int incidentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        SafetyIncident incident = session.get(SafetyIncident.class, incidentId);
        if (incident != null && "OPEN".equals(incident.getStatus())) {
            incident.setStatus("CLOSED");
        }

        tx.commit();
        session.close();
    }
}
