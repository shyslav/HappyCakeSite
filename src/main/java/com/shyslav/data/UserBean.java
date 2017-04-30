package com.shyslav.data;

import com.shyslav.mysql.exceptions.DBException;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 *         <p>
 *         This object create for all new users
 */
public class UserBean {
    //site data
    private final SiteData siteData;

    public UserBean() throws DBException {
        this.siteData = new SiteData();
    }

    public SiteData getSiteData() {
        return siteData;
    }
}
