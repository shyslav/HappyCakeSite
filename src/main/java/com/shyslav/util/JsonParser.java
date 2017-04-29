package com.shyslav.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Shyshkin Vladyslav on 28.04.2016.
 */
public class JsonParser {
    public static void Parse()
    {
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
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
