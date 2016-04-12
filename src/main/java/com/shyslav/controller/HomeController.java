package com.shyslav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String home(ModelMap map)
    {
        return "index";
    }
}
