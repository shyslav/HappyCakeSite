package com.sukhaniuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HappyCakeController {
    /**
     * @param mp
     * @param request
     * @param redirAtr
     * @return Страница меню
     */
    @RequestMapping(value = "/menu")
    public String menu(ModelMap mp, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        return "menu";
    }

    /**
     * @param id - ид категории
     * @param request
     * @param redirAtr
     * @return - страница блюд в данной категории
     */
    @RequestMapping(value="/dish/{id}")
    public String dish(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        return "dish";
    }
}
