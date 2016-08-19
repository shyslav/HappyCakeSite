package com.sukhaniuk.models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class Reservation {
    private int id;
    private int cafeId;
    private String clientName;
    private String clientPhone;
    private Date rDate;
    private Time rTime;
    private String confirmORnot;
    private int amountPeople;
    private String description;

    public Reservation(int id, int cafeId, String clientName, String clientPhone, Date rDate, Time rTime, String confirmORnot, int amountPeople, String description) {
        this.id = id;
        this.cafeId = cafeId;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.rDate = rDate;
        this.rTime = rTime;
        this.confirmORnot = confirmORnot;
        this.amountPeople = amountPeople;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCafeId() {
        return cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Time getrTime() {
        return rTime;
    }

    public void setrTime(Time rTime) {
        this.rTime = rTime;
    }

    public String isConfirmORnot() {
        return confirmORnot;
    }

    public void setConfirmORnot(String confirmORnot) {
        this.confirmORnot = confirmORnot;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }
}
