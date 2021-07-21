package com.packagename.myapp.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    public List<String> parseKeyword() {
        this.keywords = new ArrayList<>();
        String keywords = watsonController.getKeyword().trim();
        String[] keyword = keywords.split(",");

        for (String word: keyword) {
            this.keywords.add(word);
        }

        return this.keywords;
    }

}
