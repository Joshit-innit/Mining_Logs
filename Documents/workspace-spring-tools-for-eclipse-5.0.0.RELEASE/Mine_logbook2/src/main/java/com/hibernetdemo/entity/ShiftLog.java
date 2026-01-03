package com.hibernetdemo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "shift_log")
public class ShiftLog {

    @Id
    @Column(name = "shift_id")
    private int shiftId;

    @Column(name = "mine_id")
    private int mineId;

    @Column(name = "shift_date")
    private LocalDate shiftDate;

    @Column(name = "shift_type")
    private String shiftType;

    @Column(name = "supervisor_id")
    private Integer supervisorId;

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getMineId() {
        return mineId;
    }

    public void setMineId(int mineId) {
        this.mineId = mineId;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }
}
