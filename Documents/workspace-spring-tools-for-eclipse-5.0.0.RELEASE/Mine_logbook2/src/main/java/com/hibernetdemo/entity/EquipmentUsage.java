package com.hibernetdemo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment_usage")
public class EquipmentUsage {

    @Id
    @Column(name = "usage_id")
    private int usageId;

    @Column(name = "mine_id")
    private int mineId;

    @Column(name = "equipment_id")
    private int equipmentId;

    @Column(name = "usage_date")
    private LocalDate usageDate;

    @Column(name = "running_hours")
    private double runningHours;

    @Column(name = "breakdown")
    private String breakdown;

    @Column(name = "downtime_hours")
    private double downtimeHours;

    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    public int getMineId() {
        return mineId;
    }

    public void setMineId(int mineId) {
        this.mineId = mineId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public double getRunningHours() {
        return runningHours;
    }

    public void setRunningHours(double runningHours) {
        this.runningHours = runningHours;
    }

    public String getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(String breakdown) {
        this.breakdown = breakdown;
    }

    public double getDowntimeHours() {
        return downtimeHours;
    }

    public void setDowntimeHours(double downtimeHours) {
        this.downtimeHours = downtimeHours;
    }
}
