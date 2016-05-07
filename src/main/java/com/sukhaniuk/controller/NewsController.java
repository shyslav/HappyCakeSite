package com.sukhaniuk.controller;

import com.shyslav.controller.HomeController;
import com.sukhaniuk.select.selectCommand;
import com.sukhaniuk.updateCommand.updateCommands;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
@Controller
public class NewsController {
    @RequestMapping(value="news")
    public String news(ModelMap map) throws IOException, JSONException {
        map.addAttribute("webTitle","Новости");
        map.addAttribute("webMenu", HomeController.headerLoader());
        try {
            map.addAttribute("newsAll", selectCommand.selectNews(-3)); // select all news для вывода всех тегов
            map.addAttribute("news", selectCommand.selectNews(-1));  //select all news
            map.addAttribute("popularNews",selectCommand.selectNews(-2)); //select popular News
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/news.jsp";
    }
    @RequestMapping(value = "/news/{teg}")
    public String newsTeg(@PathVariable("teg") String teg, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        map.addAttribute("webTitle","Новости");
        map.addAttribute("webMenu", HomeController.headerLoader());
        try {
            map.addAttribute("news",selectCommand.selectNewsWithTeg(teg));  //select teg news
            map.addAttribute("newsAll", selectCommand.selectNews(-3)); // select all news для вывода всех тегов
            map.addAttribute("popularNews",selectCommand.selectNews(-2)); //select popular News
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "/news.jsp";
    }

    @RequestMapping(value = "/news/fullnews/{id}")
    public String newsWhereId(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        map.addAttribute("webTitle","Новости");
        map.addAttribute("webMenu", HomeController.headerLoader());
        try {
            map.addAttribute("news",selectCommand.selectNews(id));  //select news from id
            map.addAttribute("popularNews",selectCommand.selectNews(-2)); //select popular News
            map.addAttribute("newsAll", selectCommand.selectNews(-3)); // select all news для вывода всех тегов
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "/news.jsp";
    }

    @RequestMapping(value = "/news/like/{id}")
    public String likeNews(@PathVariable("id") String id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        map.addAttribute("webTitle","Новости");
        map.addAttribute("webMenu", HomeController.headerLoader());
        updateCommands.updateTable("news",new String[] {"views = views+1"},new String[] {"id = " + id});
        return "redirect:/news.htm";
    }
}