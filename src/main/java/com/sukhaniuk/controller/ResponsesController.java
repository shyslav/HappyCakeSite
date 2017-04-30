package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.shyslav.data.SiteData;
import com.shyslav.data.UserBean;
import com.shyslav.mysql.exceptions.DBException;
import com.shyslav.utils.LazyDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.happycake.sitemodels.Reports;
import com.happycake.validations.SimpleValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 05.05.2016.
 */
@Controller
public class ResponsesController extends GlobalController {
    private static final Logger log = Logger.getLogger(ResponsesController.class.getName());

    /**
     * Sent report action
     *
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to reports main page
     * @throws DBException
     */
    @RequestMapping(value = "/contacts/send")
    public String addResponses(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) throws DBException {
        log.info("controller enter to add response");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("amountsSends");
        if (tmp == null) {
            ses.setAttribute("amountsSends", 1);
            tmp = 1;
        } else if ((int) tmp >= 5) {
            redirAtr.addFlashAttribute("headModal", "Проблема");
            redirAtr.addFlashAttribute("textModal", "Ви вичерпали всі спроби створення резервації, Вам заборонено відправляти повідомлення на протязі 30 хвилин");
            return "redirect:/index.htm";
        } else {
            ses.setAttribute("amountsSends", (int) tmp + 1);
            tmp = (int) tmp + 1;
        }
        ArrayList<String> errors = new ArrayList<>();
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String message = request.getParameter("message").trim();
        errors.add(SimpleValidation.messageValidation(message));
        errors.add(SimpleValidation.nameValidation(name));
        errors.add(SimpleValidation.emailValidation(email));
        errors.add(SimpleValidation.phoneValidation(phone));
        for (int i = 0; i < errors.size(); i++) {
            if (errors.get(i).equals("done")) {
                errors.remove(i);
                i--;
            }
        }
        if (errors.size() == 0) {
            ses.setAttribute("amountsSends", null);
            redirAtr.addFlashAttribute("textModal", "Дякуємо за відгук. Ми цінуємо Вашу думку.");
            redirAtr.addFlashAttribute("headModal", "Повідомлення успішно відправлено");
            //выполнить вставку в таблицу
            Reports report = new Reports();
            report.setDate(LazyDate.getUnixDate());
            report.setAuthor(name);
            report.setMail(email);
            report.setPhone(phone);
            report.setText(message);
            SiteData.getStorage().reportsStorage.save(report);
            return "redirect:/responses.htm";
        } else {
            redirAtr.addFlashAttribute("headModal", "Обережно!");
            redirAtr.addFlashAttribute("textModal", "Повідомлення має настпуні помилки: <br>" + String.join("<br>", errors) + "<br>Увага: У вас залишилось " + (5 - (int) tmp) + " спроб");
            return "redirect:/responses.htm";
        }
    }

    /**
     * Load main reports page
     *
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return main reports page
     */
    @RequestMapping(value = "/responses")
    public String responses(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to view all responses");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Відгуки");
        map.addAttribute("webMenu", headerLoader(request));
        map.addAttribute("responses", user.getSiteData().getReports());
        return "responses.jsp";
    }
}
