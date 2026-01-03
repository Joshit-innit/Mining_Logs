package com.hibernetdemo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "production_log")
public class ProductionLog {

    @Id
    @Column(name = "prod_id")
    private int prodId;

    @Column(name = "mine_id")
    private int mineId;

    @Column(name = "shift_id")
    private Integer shiftId;

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "tonnes")
    private double tonnes;

    @Column(name = "grade")
    private double grade;

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getMineId() {
        return mineId;
    }

    public void setMineId(int mineId) {
        this.mineId = mineId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public double getTonnes() {
        return tonnes;
    }

    public void setTonnes(double tonnes) {
        this.tonnes = tonnes;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
