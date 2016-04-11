package com.shyslav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
@EnableWebMvc
public class jsonController {
    @RequestMapping(value = "/dish.json")
    public @ResponseBody
    String dish(){
        ArrayList<String> dish= new ArrayList();
        dish.add("Панини");
        dish.add("Чиабатта");
        return "hello from STRING JSON";
    }
    @RequestMapping(value = "/dish1.json")
    public @ResponseBody ArrayList<String> dish1(){
        ArrayList<String> dish= new ArrayList();
        dish.add("Панини");
        dish.add("Чиабатта");
        return dish;
    }
}
