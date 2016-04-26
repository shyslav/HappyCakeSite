package com.sukhaniuk.models;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class cafeCoordinate {
    private int id;
    private String adress;
    private String mobilePhone;

    public cafeCoordinate(int id, String adress, String mobilePhone) {
        this.id = id;
        this.adress = adress;
        this.mobilePhone = mobilePhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
