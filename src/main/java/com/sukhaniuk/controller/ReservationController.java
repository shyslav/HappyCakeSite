package com.sukhaniuk.controller;

import com.shyslav.controller.HomeController;
import com.shyslav.validation.SimpleValidation;
import com.sukhaniuk.insert.insertCommand;
import com.sukhaniuk.select.selectCommand;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class ReservationController extends SimpleValidation {
    @RequestMapping(value = "reservation")
    public String news(ModelMap map) throws IOException, JSONException {
        map.addAttribute("webTitle", "Резервация");
        map.addAttribute("webMenu", HomeController.headerLoader());
        map.addAttribute("step","first");
        return "/reservation.jsp";
    }

    @RequestMapping(value = "/reservation/{step}")
    public String newsTeg(@PathVariable("step") String step, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (step) {
            case "first":
                HttpSession ses = request.getSession();
                Object tmp = ses.getAttribute("amountReservation");
                if (tmp==null) {
                    ses.setAttribute("amountReservation",1);
                    tmp = 1;
                }
                else if((int)tmp>=5)
                {
                    //redirAtr.addFlashAttribute("alert","Вы исчерпали все попытки отправки отзыва, вам запрещено отправлять сообщения в течении 30 минут");
                    redirAtr.addFlashAttribute("headModal","Проблема");
                    redirAtr.addFlashAttribute("textModal","Вы исчерпали все попытки создания резервации, вам запрещено отправлять сообщения в течении 30 минут");
                    return "redirect:/index.htm";
                }
                else
                {
                    ses.setAttribute("amountReservation",(int)tmp+1);
                    tmp = (int)tmp +1;
                }

                String name = request.getParameter("name").trim();
                String phone = request.getParameter("phone").trim();
                String amountPeople = request.getParameter("amountpeople").trim();
                String date = request.getParameter("date").trim();
                String time = request.getParameter("time").trim();
                String message = request.getParameter("message");
                System.out.println(name + " " + phone + " " + amountPeople + " " + date + " " +time);
                if(validation(name, phone, amountPeople).size()!=0)
                {
                    redirAtr.addFlashAttribute("headModal","Вы допустили ошибки при заполнении");
                    redirAtr.addFlashAttribute("textModal",String.join("<br>",validation(name, phone, amountPeople))+"<br>Будьте внимательны, у вас осталось "+(5-(int)tmp) +" попыток");
                    return "redirect:/reservation.htm";
                }
                else {
                    insertCommand.insert("reservation", new String[]{"1", name, phone,date , time , "-", amountPeople,message}, new String[]{"cafeID", "clientName", "clientPhone", "rDate", "rTime", "confirmORnot","amountPeople","description"});
                }
                break;
            case "second":
                map.addAttribute("step","second");
                break;
        }
        return "/reservation.jsp";
    }

    private ArrayList<String> validation(String name, String phone, String amountPeople) {
        ArrayList<String> errors = new ArrayList<>();
        if (!super.nameValidation(name).equals("done")) {
            errors.add(super.nameValidation(name));
        }
        if (!super.phoneValidation(phone).equals("done")) {
            errors.add(super.phoneValidation(phone));
        }
        int amountPeoples = Integer.parseInt(amountPeople);
        if (amountPeoples < 2 || amountPeoples > 4) {
            errors.add("Не верное количество человек");
        }
        return errors;
    }

}
