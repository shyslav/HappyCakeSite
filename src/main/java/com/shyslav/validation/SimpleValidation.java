package com.shyslav.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class SimpleValidation {
    public static String nameValidation(String str) {
        if (str.length() < 3) {
            return "Слишком короткое имя";
        } else if (!str.toLowerCase().trim().matches(".*\\p{InCyrillic}.*")) //проверка на наличие только русских букв
        {
            return "Имя должно содержать только кирилицу";
        } else if (str.matches(".*\\d.*")) {
            return "Имя не может состоять из цифр";
        }
        return "done";
    }

    public static String emailValidation(String str) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return "Не верный email";
        }
        return "done";
    }

    public static String phoneValidation(String str) {
        String regex = "^\\+380\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return "Не верный телефон";
        }
        return "done";
    }

    public static String messageValidation(String str)
    {
        if(str.length()<100)
        {
            return "Текст слишком маленький";
        }
        return "done";
    }

}
