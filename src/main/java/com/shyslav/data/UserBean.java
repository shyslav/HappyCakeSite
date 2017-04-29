package com.shyslav.data;

import com.shyslav.mysql.exceptions.DBException;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 */
public class UserBean {
    private final SiteData siteData;

    public UserBean() throws DBException {
        this.siteData = new SiteData();
    }

    public SiteData getSiteData() {
        return siteData;
    }
}
