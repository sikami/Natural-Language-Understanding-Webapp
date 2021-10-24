package com.packagename.myapp.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO must be better way in getting all the result in this class using generics? find out about generics
public class Waiter {
    private List<Emotion> resultsEmotion;
    private List<SyntaxResult> syntaxResultList;
    private WatsonService watsonService;

    private Query query;

    private String queryOption;

    @Autowired
    public Waiter(Query query) throws IOException {
        this.watsonService = new WatsonService(query);
    }


}
