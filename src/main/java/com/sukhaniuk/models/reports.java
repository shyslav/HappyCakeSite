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

    public reports(int id, String author, String rText, Date rDate) {
        this.id = id;
        this.author = author;
        this.rText = rText;
        this.rDate = rDate;
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
