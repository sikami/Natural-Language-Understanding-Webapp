package com.packagename.myapp.spring;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO must be better way in getting all the result in this class using generics? find out about generics
public class Waiter {
    private WatsonService watsonService;

    @Autowired
    public Waiter(Query query) throws IOException {
        this.watsonService = new WatsonService(query);
    }

    public List<Emotion> spitEmotionResponse() {
        AnalysisResults analysisResults = this.watsonService.connectToWatson();
        List<Emotion> emotionList = this.watsonService.parseEmotion(analysisResults);

        return emotionList;

    }

    public List<SyntaxResult> spitSyntaxResponse() {
        AnalysisResults analysisResults = this.watsonService.connectToWatson();
        List<SyntaxResult> syntaxResultList = this.watsonService.parseSyntax(analysisResults);
        return syntaxResultList;
    }

    public String getKeywordResponse(int index) {
        String result = this.watsonService.parseKeyword().get(index);
        return result;
    }

    public int getKeywordSize() {
        return this.watsonService.parseKeyword().size();
    }
}
