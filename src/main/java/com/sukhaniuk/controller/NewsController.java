package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.shyslav.controller.HomeController;
import com.shyslav.data.UserBean;
import com.sukhaniuk.select.selectCommand;
import com.sukhaniuk.updateCommand.updateCommands;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
@Controller
public class NewsController extends GlobalController {
    @RequestMapping(value = "news")
    public String news(ModelMap map, HttpServletRequest request) throws IOException, JSONException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        map.addAttribute("news", user.getSiteData().getNewsList().getByArray());  //select all news
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        return "/news.jsp";
    }

    @RequestMapping(value = "/news/{teg}")
    public String newsTeg(@PathVariable("teg") String teg, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("news", user.getSiteData().getNewsList().getByTag(teg));  //select teg news
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        return "/news.jsp";
    }

    @RequestMapping(value = "/news/fullnews/{id}")
    public String newsWhereId(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("news", user.getSiteData().getNewsList().getById(id));  //select news from id
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        return "/news.jsp";
    }

    @RequestMapping(value = "/news/like/{id}")
    public String likeNews(@PathVariable("id") String id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        UserBean user = getUserInfo(request);
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("amountLike");
        if (tmp == null) {
            ses.setAttribute("amountLike", 1);
            tmp = 1;
        } else if ((int) tmp >= 2) {
            redirAtr.addFlashAttribute("headModal", "Ошибка");
            redirAtr.addFlashAttribute("textModal", "Протягом 20ти хвилин дозволено ставити тільки 2 лайки");
            return "redirect:/news.htm";
        } else {
            ses.setAttribute("amountLike", (int) tmp + 1);
            tmp = (int) tmp + 1;
        }
        redirAtr.addFlashAttribute("headModal", "Дякуємо");
        redirAtr.addFlashAttribute("textModal", "Нам важливо знати Вашу думку. За допомогою лайків ми визначаємо, що Вам найбільше до вподоби. У Вас залишився ще " + (2 - (int) tmp) + " лайк, Ви можете віддати його будь-якому запису");
        updateCommands.updateTable("news", new String[]{"views = views+1"}, new String[]{"id = " + id});
        return "redirect:/news.htm";
    }
}
