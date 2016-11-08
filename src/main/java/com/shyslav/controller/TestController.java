package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import database.configuration.DatabaseConnection;
import database.select.SelectCommand;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sitemodels.*;
import sitestorages.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shyslav on 20.08.16.
 */
@Controller
public class TestController extends GlobalController {
    @RequestMapping(value="imageTest")
    public void imageTest(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","lol");

        //String command = "select * from blobTest";
        String command = "select * from dish";
        DatabaseConnection db = new DatabaseConnection();
        db.openConnection();
        InputStream inputStream = null;
        try {
            db.rs = db.st.executeQuery(command);
            while (db.rs.next()) {
                inputStream = db.rs.getBinaryStream("image");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
        }
        if(inputStream == null)
        {
        }
        response.getOutputStream().write(IOUtils.toByteArray(inputStream));
        response.setContentType("image/gif");
    }
}
