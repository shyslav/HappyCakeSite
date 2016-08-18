package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
public class HomeController extends GlobalController {
    @RequestMapping(value="index")
    public String home(ModelMap map , HttpServletRequest request) throws IOException, JSONException, SQLException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","Главная");
        map.addAttribute("webMenu",headerLoader(request));
        map.addAttribute("hotPrice", hotPriceLoader(request));
        map.addAttribute("randCategory", randCategory(request));
        return "index.jsp";
    }
    @RequestMapping(value="contacts")
    public String contacts(ModelMap map, HttpServletRequest request) throws IOException, JSONException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","Контакты");
        map.addAttribute("webMenu",headerLoader(request));
        map.addAttribute("contacts",user.getSiteData().getCafeCoordinates());
        return "contacts.jsp";
    }
    @RequestMapping(value="lalal")
    public String lalal(ModelMap map, HttpServletRequest request) throws IOException, JSONException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","lol");
        return "index.jsp";
    }
}
