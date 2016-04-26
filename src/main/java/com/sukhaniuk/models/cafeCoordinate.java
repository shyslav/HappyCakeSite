package com.sukhaniuk.models;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class cafeCoordinate {
    private int id;
    private String address;
    private String mobilePhone;

    public cafeCoordinate(int id, String address, String mobilePhone) {
        this.id = id;
        this.address = address;
        this.mobilePhone = mobilePhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
