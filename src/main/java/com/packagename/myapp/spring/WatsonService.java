package com.packagename.myapp.spring;

import java.util.ArrayList;
import java.util.List;

public class WatsonService {
    private List<String> keywords;

    public WatsonService() {
        this.keywords = new ArrayList<>();
    }

    public static boolean connectToWatson(String option) {
        if (option.equals("Emotion") || option.equals("Syntax")) {
            return true;
        }
        return false;
    }

    public List<String> parseKeyword() {
        WatsonController watsonController = new WatsonController();
        return this.keywords;
    }

}
