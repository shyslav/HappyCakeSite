package com.sukhaniuk.controller;

import com.happycake.GlobalController;
import com.shyslav.data.SiteData;
import com.shyslav.data.UserBean;
import com.shyslav.mysql.exceptions.DBException;
import com.shyslav.utils.LazyDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.happycake.sitemodels.PreOrder;
import com.happycake.sitemodels.Reservation;
import com.happycake.sitemodels.ReservationData;
import com.happycake.validations.SimpleValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@SuppressWarnings("unused")
@Controller
public class ReservationController extends GlobalController {
    private static final Logger log = Logger.getLogger(ReservationController.class.getName());

    /**
     * Reservation
     *
     * @param map     Action response map
     * @param request Action request
     * @return reservation page
     * @throws IOException
     */
    @RequestMapping(value = "/reservation")
    public String reservationData(ModelMap map, HttpServletRequest request) throws IOException {
        log.info("controller enter to reservation");
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle", "Бронювання");
        map.addAttribute("webMenu", headerLoader(request));
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("reservationConfig");
        if (tmp != null) {
            map.addAttribute("step", "second");
            map.addAttribute("dish", user.getSiteData().getDishes());
        }
        return "/reservation.jsp";
    }

    /**
     * Load reservation content by step
     *
     * @param step     step string (first or second)
     * @param map      action response map
     * @param request  action request
     * @param redirAtr redirect attribute
     * @return reservation form by step
     */
    @RequestMapping(value = "/reservation/{step}")
    public String reservationStep(@PathVariable("step") String step, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to reservation by step where step = " + step);
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
                    redirAtr.addFlashAttribute("alert", "Вы исчерпали все попытки отправки отзыва, вам запрещено отправлять сообщения в течении 30 минут");
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
                    ArrayList<ReservationData> reservationData = new ArrayList<>();
                    reservationData.add(new ReservationData(name, phone, amountPeople, date, time, message));
                    ses.setAttribute("reservationConfig", reservationData);
                }
                break;
            case "second":
                break;
        }
        return "redirect:/reservation.htm";
    }


    /**
     * Add preorder to reservation
     *
     * @param id       dish id
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to reservation page
     */
    @RequestMapping(value = "/reservation/addpreorder/{id}")
    public String newsTeg(@PathVariable("id") int id, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to addpreorder by id where id = " + id);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("preOrderList");
        Object aboutUser = ses.getAttribute("reservationConfig");
        if (aboutUser == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Час сесії минув. Введіть дані знову");
            return "redirect:/reservation.htm";
        }
        ArrayList<PreOrder> preOrders = new ArrayList<>();
        int amount = Integer.parseInt(request.getParameter("amount"));
        double price = Double.parseDouble(request.getParameter("price"));
        String dishName = request.getParameter("dishName");
        if (tmp == null) {
            preOrders.add(new PreOrder(id, amount, amount * price));
            ses.setAttribute("preOrderList", preOrders);
        } else {
            preOrders = (ArrayList<PreOrder>) ses.getAttribute("preOrderList");
            boolean comp = false;
            for (PreOrder order : preOrders) {
                if (order.getDishID() == id) {
                    order.setAmount(order.getAmount() + amount);
                    order.setPrice(order.getAmount() * price);
                    comp = true;
                    break;
                }
            }
            if (!comp) {
                preOrders.add(new PreOrder(id, amount, amount * price));
            }
            ses.setAttribute("preOrderList", preOrders);
        }
        return "redirect:/reservation.htm";
    }

    /**
     * Delete dish from preorder list
     *
     * @param delete   id of dish
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to reservation main page
     */
    @RequestMapping(value = "/reservation/delete/{delete}")
    public String delete(@PathVariable("delete") int delete, ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to delete dish by id where id = " + delete);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ArrayList<PreOrder> preOrders;
        HttpSession ses = request.getSession();
        Object tmp = ses.getAttribute("preOrderList");
        if (tmp == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Ви намагаєтесь видалити недобавлену до кошика сраву.");
        } else {
            preOrders = (ArrayList<PreOrder>) ses.getAttribute("preOrderList");
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

    /**
     * Change reservation date
     *
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to reservation main page
     */
    @RequestMapping(value = "/reservation/changeData")
    public String changeData(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) {
        log.info("controller enter to change reservation data");
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

    /**
     * Finish reservation process
     *
     * @param map      action response map
     * @param request  action request
     * @param redirAtr action redirect attributes
     * @return redirect to main reservation page
     * @throws DBException
     */
    @RequestMapping(value = "/reservation/complite")
    public String delete(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr) throws DBException {
        log.info("controller enter complite reservation");
        UserBean user = getUserInfo(request);
        HttpSession ses = request.getSession();
        Object reservationConfig = ses.getAttribute("reservationConfig");
        Object preOrderList = ses.getAttribute("preOrderList");
        if (reservationConfig == null || preOrderList == null) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Ви намагались виконати невірний запит");
            return "redirect:/reservation.htm";
        }
        ArrayList<PreOrder> preOrders;
        preOrders = (ArrayList<PreOrder>) preOrderList;
        ArrayList<ReservationData> resData = (ArrayList<ReservationData>) reservationConfig;
        //get sum
        double sum = 0.0;
        for (PreOrder order : preOrders) {
            sum += order.getPrice() * order.getAmount();
        }
        if (sum < 150.0) {
            redirAtr.addFlashAttribute("headModal", "Помилка");
            redirAtr.addFlashAttribute("textModal", "Сума замовлення менша за 150 грн");
        } else {
            Reservation reservation = new Reservation();
            reservation.setClientName(resData.get(0).getName());
            reservation.setClientPhone(resData.get(0).getPhone());
            reservation.setAmountPeople(Integer.parseInt(resData.get(0).getAmountPeople()));
            reservation.setDate(LazyDate.getUnixDate());
            reservation.setDescription(resData.get(0).getMessage());
            reservation.setClientName(resData.get(0).getName());
            reservation.setCafeId(user.getSiteData().getCafeCoordinates().get(0).getId());
            SiteData.getStorage().reservationStorage.save(reservation);
            int max = SiteData.getStorage().reservationStorage.getMaxID();
            for (PreOrder order : preOrders) {
                order.setReservationID(max);
                SiteData.getStorage().preOrderStorage.save(order);
            }
            ses.removeAttribute("reservationConfig");
            ses.removeAttribute("preOrderList");
            ses.removeAttribute("amountReservation");
            redirAtr.addFlashAttribute("headModal", "Дякуємо за бронювання столика");
            redirAtr.addFlashAttribute("textModal", "Протягом 30 хвилин з Вами зв'яжеться адміністратор і уточнить всі деталі.");
        }
        return "redirect:/reservation.htm";
    }

    /**
     * Check if client reservation data is valid
     *
     * @param name         client name
     * @param phone        client phone
     * @param amountPeople amount peoples
     * @return list of errors
     */
    private ArrayList<String> validation(String name, String phone, String amountPeople) {
        ArrayList<String> errors = new ArrayList<>();
        if (!SimpleValidation.nameValidation(name).equals("done")) {
            errors.add(SimpleValidation.nameValidation(name));
        }
        if (!SimpleValidation.phoneValidation(phone).equals("done")) {
            errors.add(SimpleValidation.phoneValidation(phone));
        }
        int amountPeoples = Integer.parseInt(amountPeople);
        if (amountPeoples < 2 || amountPeoples > 4) {
            errors.add("Невірна кількість людей");
        }
        return errors;
    }
}
