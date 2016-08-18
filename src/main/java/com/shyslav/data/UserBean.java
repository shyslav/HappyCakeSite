package com.shyslav.data;

import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class UserBean {
    private final SiteData siteData;

    public UserBean() throws SQLException {
        this.siteData = new SiteData();
    }

    public SiteData getSiteData() {
        return siteData;
    }
}
