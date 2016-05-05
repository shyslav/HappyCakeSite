package com.sukhaniuk.controller;

import com.shyslav.controller.HomeController;
import com.shyslav.validation.SimpleValidation;
import com.sukhaniuk.insert.insertCommand;
import com.sukhaniuk.updateCommand.updateCommands;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ResponsesController extends SimpleValidation{
    @RequestMapping(value = "/contacts/send")
    public String likeNews(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
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
            redirAtr.addFlashAttribute("textModal","Вы исчерпали все попытки отправки отзыва, вам запрещено отправлять сообщения в течении 30 минут");
            return "redirect:/index.htm";
        }
        else
        {
            ses.setAttribute("amountsSends",(int)tmp+1);
            tmp = (int)tmp +1;
        }
        ArrayList<String> errors = new ArrayList<>();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        errors.add(super.messageValidation(message));
        errors.add(super.nameValidation(name));
        errors.add(super.emailValidation(email));
        errors.add(super.phoneValidation(phone));
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
            //redirAtr.addFlashAttribute("alert","Сообщение успешно отправлено");
            redirAtr.addFlashAttribute("textModal","Спасибо за ваш отзыв, все ваши отзывы очень важны");
            redirAtr.addFlashAttribute("headModal","Сообщение успешно отправлено");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            insertCommand.insert("reports",new String [] {name,message,dateFormat.format(date)},new String [] {"author","rText","rDate"});
            return "redirect:/index.htm";
        }
        else
        {
            //redirAtr.addFlashAttribute("alert","Сообщение имеет следующие ошибки: \\n"+String.join("\\n",errors)+"\\n Внимание: У вас осталось " + (5 - (int)tmp) +" попыток");
            redirAtr.addFlashAttribute("headModal","Осторожно!");
            redirAtr.addFlashAttribute("textModal","Сообщение имеет следующие ошибки: <br>"+String.join("<br>",errors)+"<br>Внимание: У вас осталось " + (5 - (int)tmp) +" попыток");
            return "redirect:/contacts.htm";
        }
    }
}
