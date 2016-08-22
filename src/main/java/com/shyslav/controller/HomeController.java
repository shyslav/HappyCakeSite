package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/dishImage/{id}")
    public void dishImage(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException {
        UserBean user = getUserInfo(request);
        response.getOutputStream().write(user.getSiteData().getDishes().getById(id).getImage());
        response.setContentType("image/gif");
    }

    @RequestMapping(value = "/categoryImage/{id}")
    public void categoryImage(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException {
        UserBean user = getUserInfo(request);
        response.getOutputStream().write(user.getSiteData().getCategories().getById(id).getImage());
        response.setContentType("image/gif");
    }

}
