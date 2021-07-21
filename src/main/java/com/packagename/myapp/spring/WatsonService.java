package com.packagename.myapp.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WatsonService {
    private List<String> keywords;
    private WatsonController watsonController;

    public WatsonService() {
        this.watsonController = new WatsonController();
    }

    public WatsonService(WatsonController watsonController) {
        this.watsonController = watsonController;
    }


    public static boolean optionChooser(String option) {
        if (option.equals("Emotion") || option.equals("Syntax")) {
            return true;
        }
        return false;
    }

    /**
     * This method populates keywords. Important for Query to be declared in WatsonController.
     * Method has to be executed everytime we want to send for keyword to IBM
     * @return
     */
    public List<String> parseKeyword() {
        this.keywords = new ArrayList<>();
        String keys = watsonController.getKeyword();
        String[] keyword = keys.split(",");

        for (String word: keyword) {
            this.keywords.add(word.trim().toLowerCase());
        }

        return this.keywords;
    }

}
