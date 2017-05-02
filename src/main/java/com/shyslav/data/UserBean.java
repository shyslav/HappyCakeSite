package com.shyslav.data;

import com.shyslav.mysql.exceptions.DBException;
import org.apache.log4j.Logger;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 *         <p>
 *         This object create for all new users
 */
public class UserBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    //site data
    private SiteData siteData;

    public UserBean() {
        try {
            this.siteData = new SiteData();
        } catch (DBException e) {
            log.error("Unable to lod site data " + " " + e.getMessage(), e);
        }
    }

    /**
     * Get site data
     *
     * @return site data info
     */
    public SiteData getSiteData() {
        return siteData;
    }
}
