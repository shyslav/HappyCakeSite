package com.happycake;

import com.shyslav.data.UserBean;
import com.sukhaniuk.models.category;
import com.sukhaniuk.models.hotPrice;
import com.sukhaniuk.models.webMenu;
import com.sukhaniuk.select.selectCommand;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class GlobalController {
    protected UserBean getUserInfo(HttpServletRequest req){
        if(req.getSession().getAttribute("userBean") == null){
            try {
                UserBean userBean = new UserBean();
                req.getSession().setAttribute("userBean",userBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (UserBean) req.getSession().getAttribute("userBean");
    }
    protected ArrayList<webMenu> headerLoader (HttpServletRequest request)
    {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }
    protected ArrayList<hotPrice> hotPriceLoader(HttpServletRequest request)
    {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getHotPrices();
    }
    protected ArrayList<category> randCategory(HttpServletRequest request)
    {
        ArrayList<category> category = null;
        try {
            category = selectCommand.selectRandCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
