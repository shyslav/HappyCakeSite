package com.shyslav.controller;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String home(ModelMap map) throws IOException, JSONException {
        return "index";
    }
    @RequestMapping(value="lalal")
    public String lalal(ModelMap map) throws IOException, JSONException {
        JSONParser parser = new JSONParser();
        try {
            URL oracle = new URL("http://localhost:8080/dish.json"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF8"));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);

                // Loop through each item
                for (Object o : a) {
                    JSONObject tutorials = (JSONObject) o;

                    Long id = (Long) tutorials.get("id");
                    System.out.println("Post ID : " + id);

                    String title = (String) tutorials.get("name");
                    System.out.println("Post Title : " + title);

                    System.out.println("\n");
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
