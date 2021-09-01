package com.packagename.myapp.spring;

import java.util.ArrayList;
import java.util.List;

public class WatsonService {

    private WatsonController watsonController;

    public WatsonService(WatsonController watsonController) {
        this.watsonController = watsonController;
    }


    //moving this to query
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
        List<String> keywords = new ArrayList<>();
        String keys = watsonController.getKeyword();
        String[] keyword = keys.split(",");

        for (String word: keyword) {
            keywords.add(word.trim().toLowerCase());
        }

        return keywords;
    }

}
