package com.sukhaniuk.models;

/**
 * Created by Marina on 12.04.2016.
 */
public class webMenu {
    private int id;
    private String name;
    private String link;
    private int menusort;

    public webMenu(int id,String name, String link,int menusort) {
        this.id = id;
        this.menusort = menusort;
        this.link = link;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getMenusort() {
        return menusort;
    }

    public void setMenusort(int menusort) {
        this.menusort = menusort;
    }
}
