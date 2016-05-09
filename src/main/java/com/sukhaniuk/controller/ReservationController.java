package com.sukhaniuk.controller;

import com.shyslav.controller.HomeController;
import com.shyslav.validation.SimpleValidation;
import com.sukhaniuk.insert.insertCommand;
import com.sukhaniuk.models.preOrder;
import com.sukhaniuk.models.reservationData;
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
    @RequestMapping(value = "/reservation")
    public String news(ModelMap map, HttpServletRequest request) throws IOException, JSONException {
        map.addAttribute("webTitle", "Бронювання");
        map.addAttribute("webMenu", HomeController.headerLoader());
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("reservationConfig");
        if (tmp != null) {
            map.addAttribute("step", "second");
            try {
                map.addAttribute("dish", selectCommand.selectdish(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
                if (tmp == null) {
                    ses.setAttribute("amountReservation", 1);
                    tmp = 1;
                } else if ((int) tmp >= 5) {
                    //redirAtr.addFlashAttribute("alert","Вы исчерпали все попытки отправки отзыва, вам запрещено отправлять сообщения в течении 30 минут");
                    redirAtr.addFlashAttribute("headModal", "Проблема");
                    redirAtr.addFlashAttribute("textModal", "Ви вичерпали всі спроби створення резервації, Вам заборонено відправляти повідомлення на протязі 30 хвилин");
                    return "redirect:/index.htm";
                }
                String name = request.getParameter("name").trim();
                String phone = request.getParameter("phone").trim();
                String amountPeople = request.getParameter("amountpeople").trim();
                String date = request.getParameter("date").trim();
                String time = request.getParameter("time").trim();
                String message = request.getParameter("message");
                System.out.println(name + " " + phone + " " + amountPeople + " " + date + " " + time);
                if (validation(name, phone, amountPeople).size() != 0) {
                    //Увеличить счетчик не правильных попыток
                    ses.setAttribute("amountReservation", (int) tmp + 1);
                    redirAtr.addFlashAttribute("headModal", "Вы допустили ошибки при заполнении");
                    redirAtr.addFlashAttribute("textModal", String.join("<br>", validation(name, phone, amountPeople)) + "<br>Будьте уважні, у Вас залишилось " + (5 - (int) tmp) + " спроб");
                    return "redirect:/reservation.htm";
                } else {
                    redirAtr.addFlashAttribute("step", "second");
                    ArrayList<reservationData> reservationData = new ArrayList<>();
                    reservationData.add(new reservationData(name, phone, amountPeople, date, time, message));
                    ses.setAttribute("reservationConfig", reservationData);
                }
                break;
            case "second":
                break;
        }
        return "redirect:/reservation.htm";
    }


    @RequestMapping(value = "/reservation/addpreorder/{id}")
    public String newsTeg(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("preOrderList");
        Object aboutUser = ses.getAttribute("reservationConfig");
        if(aboutUser==null)
        {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Час сесії минув. Введіть дані знову");
            return "redirect:/reservation.htm";
        }
        ArrayList<preOrder> preOrders = new ArrayList<>();
        int amount = Integer.parseInt(request.getParameter("amount"));
        double price = Double.parseDouble(request.getParameter("price"));
        String dishName = request.getParameter("dishName");
        if (tmp == null) {
            preOrders.add(new preOrder(id, dishName, amount, amount * price));
            ses.setAttribute("preOrderList", preOrders);
        } else {
            preOrders = (ArrayList<preOrder>) ses.getAttribute("preOrderList");
            boolean comp = false;
            for (int i = 0; i < preOrders.size(); i++) {
                if (preOrders.get(i).getDishID() == id) {
                    preOrders.get(i).setAmount(preOrders.get(i).getAmount() + amount);
                    preOrders.get(i).setPrice(preOrders.get(i).getAmount()*price);
                    comp = true;
                    break;
                }
            }
            if(!comp) {
                preOrders.add(new preOrder(id, dishName, amount,amount*price));
            }
            ses.setAttribute("preOrderList", preOrders);
        }
        return "redirect:/reservation.htm";
    }

    @RequestMapping(value = "/reservation/delete/{delete}")
    public String delete(@PathVariable("delete") int delete, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ArrayList<preOrder> preOrders = new ArrayList<>();
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("preOrderList");
        if (tmp == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Ви намагаєтесь видалити недобавлену до кошика сраву.");
        } else {
            preOrders = (ArrayList<preOrder>) ses.getAttribute("preOrderList");
            for (int i = 0; i < preOrders.size(); i++) {
                if (preOrders.get(i).getDishID() == delete) {
                    preOrders.remove(i);
                    ses.setAttribute("preOrderList", preOrders);
                    break;
                }
            }
        }
        return "redirect:/reservation.htm";
    }

    @RequestMapping(value = "/reservation/changeData")
    public String changeData(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("reservationConfig");
        if (tmp == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Ви намагались виконати невірний запит");
        } else {
            ses.removeAttribute("reservationConfig");
        }
        return "redirect:/reservation.htm";
    }

    @RequestMapping(value = "/reservation/complite")
    public String delete(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        HttpSession ses = request.getSession();
        Object reservationConfig = ses.getAttribute("reservationConfig");
        Object preOrderList = ses.getAttribute("preOrderList");
        if (reservationConfig == null || preOrderList == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Ви намагались виконати невірний запит");
            return "redirect:/reservation.htm";
        }
        ArrayList<preOrder> preOrders = new ArrayList<>();
        preOrders = (ArrayList<preOrder>) preOrderList;
        ArrayList<reservationData> resData = new ArrayList<>();
        resData = (ArrayList<reservationData>) reservationConfig;
        //get sum
        double sum = 0.0;
        for (int i = 0; i < preOrders.size(); i++) {
            sum += preOrders.get(i).getPrice() * preOrders.get(i).getAmount();
        }
        if (sum < 150.0) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Сума замовлення менша за 150 грн");
        } else {
            int max = selectCommand.selectMaxFromReservation() + 1;
            insertCommand.insert("reservation", new String[]{"1", resData.get(0).getName(), resData.get(0).getPhone(), resData.get(0).getDate(), resData.get(0).getTime(), "-", resData.get(0).getAmountPeople(), resData.get(0).getMessage()}, new String[]{"cafeID", "clientName", "clientPhone", "rDate", "rTime", "confirmORnot", "amountPeople", "description"});
            for (int i = 0; i < preOrders.size(); i++) {
                insertCommand.insert("preorder", new String[]{String.valueOf(max), String.valueOf(preOrders.get(i).getDishID()), String.valueOf(preOrders.get(i).getAmount()), String.valueOf(preOrders.get(i).getPrice())}, new String[]{"reservID", "dishID", "amount", "price"});
            }
            ses.removeAttribute("reservationConfig");
            ses.removeAttribute("preOrderList");
            ses.removeAttribute("amountReservation");
            redirAtr.addFlashAttribute("headModal", "Дякуємо за бронювання столика");
            redirAtr.addFlashAttribute("textModal", "Протягом 30 хвилин з Вами зв'яжеться адміністратор і уточнить всі деталі.");
        }
        return "redirect:/reservation.htm";
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
            errors.add("Невірна кількість людей");
        }
        return errors;
    }
}
