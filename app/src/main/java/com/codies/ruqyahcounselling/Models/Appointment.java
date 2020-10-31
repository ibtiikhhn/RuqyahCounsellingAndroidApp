package com.codies.ruqyahcounselling.Models;

import java.io.Serializable;

public class Appointment implements Serializable {
    String userId;
    String appointmentId;
    String dueDate;
    String dueTime;
    String status;

    public Appointment() {
    }

    public Appointment(String userId, String appointmentId, String dueDate, String dueTime, String status) {
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
