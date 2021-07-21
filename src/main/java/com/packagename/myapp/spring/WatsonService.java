package com.packagename.myapp.spring;

public class WatsonService {

    public static boolean connectToWatson(String option) {
        if (option.equals("Emotion") || option.equals("Syntax")) {
            return true;
        }
        return false;
    }
}
