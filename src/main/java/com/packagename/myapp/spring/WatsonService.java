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

    //Query has to be delcared in WatsonController
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
