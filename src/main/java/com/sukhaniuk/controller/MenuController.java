package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shyshkin Vladyslav on 04.05.2016.
 */
@SuppressWarnings("unused")
@Controller
public class MenuController extends GlobalController {
    private static final Logger log = Logger.getLogger(MenuController.class.getName());

    /**
     * Get restaurant menu
     *
     * @param map      model map
     * @param request  action request
     * @param redirAtr redirect attributes
     * @return menu page
     */
    @RequestMapping(value = "/menu")
    public String menu(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to menu");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Категорії");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("category", user.getSiteData().getCategories());
        return "menu.jsp";
    }

    /**
     * Get category by id
     *
     * @param id       if of category
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return - страница блюд в данной категории
     */
    @RequestMapping(value = "/category/{id}")
    public String dish(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller category enter by id = " + id);
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Страви");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("dish", user.getSiteData().getDishes().getByCategoryId(id));
        return "/dish.jsp";
    }
}
