package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import com.shyslav.validation.SimpleValidation;
import com.sukhaniuk.insert.DatabaseInsert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
@Controller
public class ResponsesController extends GlobalController {
    @RequestMapping(value = "/contacts/send")
    public String addResponses(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("amountsSends");
        if (tmp==null) {
            ses.setAttribute("amountsSends",1);
            tmp = 1;
        }
        else if((int)tmp>=5)
        {
            //redirAtr.addFlashAttribute("alert","Вы исчерпали все попытки отправки отзыва, вам запрещено отправлять сообщения в течении 30 минут");
            redirAtr.addFlashAttribute("headModal","Проблема");
            redirAtr.addFlashAttribute("textModal","Ви вичерпали всі спроби створення резервації, Вам заборонено відправляти повідомлення на протязі 30 хвилин");
            return "redirect:/index.htm";
        }
        else
        {
            ses.setAttribute("amountsSends",(int)tmp+1);
            tmp = (int)tmp +1;
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
        for (int i = 0; i < errors.size();i++)
        {
            if(errors.get(i).equals("done"))
            {
                errors.remove(i);
                i--;
            }
        }
        if(errors.size()==0)
        {
            ses.setAttribute("amountsSends",null);
            //redirAtr.addFlashAttribute("alert","Сообщение успешно отправлено");
            redirAtr.addFlashAttribute("textModal","Дякуємо за відгук. Ми цінуємо Вашу думку.");
            redirAtr.addFlashAttribute("headModal","Повідомлення успішно відправлено");
            //задать формат даты
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            //выполнить вставку в таблицу
            DatabaseInsert.insert("reports",new String [] {name,message,dateFormat.format(date),email,phone,"-"},new String [] {"author","rText","rDate","mail","phone","vision"});
            return "redirect:/responses.htm";
        }
        else
        {
            //redirAtr.addFlashAttribute("alert","Сообщение имеет следующие ошибки: \\n"+String.join("\\n",errors)+"\\n Внимание: У вас осталось " + (5 - (int)tmp) +" попыток");
            redirAtr.addFlashAttribute("headModal","Обережно!");
            redirAtr.addFlashAttribute("textModal","Повідомлення має настпуні помилки: <br>"+String.join("<br>",errors)+"<br>Увага: У вас залишилось " + (5 - (int)tmp) +" спроб");
            return "redirect:/responses.htm";
        }
    }
    @RequestMapping(value = "/responses")
    public String responses(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","Відгуки");
        map.addAttribute("webMenu",headerLoader(request));
        map.addAttribute("responses", user.getSiteData().getRepartees());
        return "responses.jsp";
    }
}
