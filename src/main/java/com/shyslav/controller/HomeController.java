package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import org.apache.log4j.Logger;
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
 * @author Shyshkin Vladyslav on 10.04.16.
 */
@SuppressWarnings("unused")
@Controller
public class HomeController extends GlobalController {
    private static final Logger log = Logger.getLogger(HomeController.class.getName());

    /**
     * load index page
     *
     * @param map      model map
     * @param request  action request
     * @param response action response
     * @param redirAtr redirect attribute
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "index")
    public String home(ModelMap map, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException, SQLException {
        log.info("controller enter to index");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Главная");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("hotPrice", hotPriceLoader(request));
        map.addAttribute("randCategory", randCategory(request, 3));
        return "index.jsp";
    }

    /**
     * Get contacts
     *
     * @param map      model map
     * @param request  action request
     * @param response action response
     * @param redirAtr redirect attribute
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "contacts")
    public String contacts(ModelMap map, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException {
        log.info("controller enter to contacts");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Контакты");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("contacts",user.getSiteData().getCafeCoordinates());
        return "contacts.jsp";
    }

    /**
     * Get dish image stream
     *
     * @param id       dish id
     * @param request  action request
     * @param response action response
     * @param redirAtr redirect attribute
     * @throws IOException
     */
    @RequestMapping(value = "/dishImage/{id}")
    public void dishImage(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException {
        UserBean user = getUserInfo(request);
        response.getOutputStream().write(user.getSiteData().getDishes().getByID(id).getImage());
        response.setContentType("image/gif");
    }

    /**
     * Get category image stream
     *
     * @param id       category id
     * @param request  action request
     * @param response action response
     * @param redirAtr redirect attribute
     * @throws IOException
     */
    @RequestMapping(value = "/categoryImage/{id}")
    public void categoryImage(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirAtr) throws IOException {
        UserBean user = getUserInfo(request);
        response.getOutputStream().write(user.getSiteData().getCategories().getByID(id).getImage());
        response.setContentType("image/gif");
    }

}
