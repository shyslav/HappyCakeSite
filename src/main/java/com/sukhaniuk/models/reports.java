package com.sukhaniuk.models;

import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class reports {
    private int id;
    private String author;
    private String rText;
    private Date rDate;
    private String mail;
    private String phone;
    private String vision;

    public reports(int id, String author, String rText, Date rDate, String mail, String phone, String vision) {
        this.id = id;
        this.author = author;
        this.rText = rText;
        this.rDate = rDate;
        this.mail = mail;
        this.phone = phone;
        this.vision = vision;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getrText() {
        return rText;
    }

    public void setrText(String rText) {
        this.rText = rText;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }
}
