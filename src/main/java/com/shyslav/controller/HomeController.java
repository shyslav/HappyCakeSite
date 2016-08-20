package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import com.shyslav.util.DatabaseConnection;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
public class HomeController extends GlobalController {
    @RequestMapping(value="index")
    public String home(ModelMap map , HttpServletRequest request) throws IOException, JSONException, SQLException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","Главная");
        map.addAttribute("webMenu",headerLoader(request));
        map.addAttribute("hotPrice", hotPriceLoader(request));
        map.addAttribute("randCategory", randCategory(request));
        return "index.jsp";
    }
    @RequestMapping(value="contacts")
    public String contacts(ModelMap map, HttpServletRequest request) throws IOException, JSONException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","Контакты");
        map.addAttribute("webMenu",headerLoader(request));
        map.addAttribute("contacts",user.getSiteData().getCafeCoordinates());
        return "contacts.jsp";
    }
    @RequestMapping(value="lalal")
    public void lalal(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        UserBean user = getUserInfo(request);
        map.addAttribute("webTitle","lol");

        String command = "select * from blobTest";
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
