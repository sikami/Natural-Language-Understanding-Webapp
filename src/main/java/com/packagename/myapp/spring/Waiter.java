package com.packagename.myapp.spring;

import java.util.List;

//TODO must be better way in getting all the result in this class using generics? find out about generics
public class Waiter {
    private List<Emotion> resultsEmotion;
    private List<SyntaxResult> syntaxResultList;
    private WatsonService watsonService;
    private Query query;
    private String queryOption;

    public Waiter(List<Emotion> emotionList, String query) {
        this.resultsEmotion = emotionList;
        this.queryOption = query;
    }

    public Waiter(List<SyntaxResult> syntaxResultList) {
        this.syntaxResultList = syntaxResultList;
    }

    public void setQueryOption(String queryOption) {
        this.queryOption = queryOption;
    }
}
