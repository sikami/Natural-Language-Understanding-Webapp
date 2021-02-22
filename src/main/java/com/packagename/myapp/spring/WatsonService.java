package com.packagename.myapp.spring;

public class WatsonService {

    private Query query = new Query();

    public static boolean connectToWatson(String option) {
        if (option.equals("Emotion")) {

            return true;
        }
        return false;
    }
}
