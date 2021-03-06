package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.happycake.sitemodels.News;
import com.shyslav.data.SiteData;
import com.shyslav.data.UserBean;
import com.shyslav.mysql.exceptions.DBException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 05.05.2016.
 */

@Controller
public class NewsController extends GlobalController {
    private static final Logger log = Logger.getLogger(NewsController.class.getName());

    /**
     * Get news
     *
     * @param map     response map
     * @param request action request
     * @return path to news
     * @throws IOException
     */
    @RequestMapping(value = "news")
    public String news(ModelMap map, HttpServletRequest request) throws IOException {
        log.info("controller enter to news");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        map.addAttribute("news", user.getSiteData().getNewsList());  //select all news
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        return "/news.jsp";
    }

    /**
     * Load news by tag
     *
     * @param teg      news tag
     * @param map      response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return news array by tag name
     */
    @RequestMapping(value = "/news/{teg}")
    public String newsTeg(@PathVariable("teg") String teg, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to news by teg where teg = " + teg);
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("news", user.getSiteData().getNewsList().getByTag(teg));  //select teg news
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        return "/news.jsp";
    }

    /**
     * Load full news
     *
     * @param id       news id
     * @param map      response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return full news data
     */
    @RequestMapping(value = "/news/fullnews/{id}")
    public String newsWhereId(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to fullnews by id where id = " + id);
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Новини");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("news", user.getSiteData().getNewsList().getByIDS(new int[]{id}));  //select news from id
        map.addAttribute("popularNews", user.getSiteData().getNewsList().getPopular()); //select popular News
        map.addAttribute("newsAll", user.getSiteData().getNewsList().uniqueTagArray()); // select all news для вывода всех тегов
        return "/news.jsp";
    }

    /**
     * Like news
     *
     * @param id       id of news
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to index news page
     */
    @RequestMapping(value = "/news/like/{id}")
    public String likeNews(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to news like by id where id = " + id);
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
        News byID = user.getSiteData().getNewsList().getByID(id);
        byID.increaseViews();
        try {
            SiteData.getStorage().newsStorage.update(byID, byID.getId());
        } catch (DBException e) {
            log.trace("unable to update news storage " + e.getMessage(), e);
        }
        return "redirect:/news.htm";
    }
}
