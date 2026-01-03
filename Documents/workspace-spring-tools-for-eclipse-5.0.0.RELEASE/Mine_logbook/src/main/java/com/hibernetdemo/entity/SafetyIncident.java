package com.hibernetdemo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "safety_incident")
public class SafetyIncident {

    @Id
    @Column(name = "incident_id")
    private int incidentId;

    @Column(name = "mine_id")
    private int mineId;

    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "worker_id")
    private Integer workerId;

    @Column(name = "incident_date")
    private LocalDate incidentDate;

    @Column(name = "type")
    private String type;

    @Column(name = "severity")
    private int severity;

    @Column(name = "cost")
    private double cost;

    @Column(name = "status")
    private String status;

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public int getMineId() {
        return mineId;
    }

    public void setMineId(int mineId) {
        this.mineId = mineId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
