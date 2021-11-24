package com.packagename.myapp.spring;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//TODO must be better way in getting all the result in this class using generics? find out about generics
public class Waiter {
    private WatsonService watsonService;
    private static Logger logger = Logger.getLogger(Waiter.class.getName());

    @Autowired
    public Waiter(Query query) throws IOException {
        this.watsonService = new WatsonService(query);
    }

    public List<Emotion> spitEmotionResponse() {
        List<Emotion> emotionList = new ArrayList<>();
        try {
            AnalysisResults analysisResults = this.watsonService.connectToWatson();
            emotionList = this.watsonService.parseEmotion(analysisResults);
        } catch (Exception e) {
            logger.warning("If you see this warning, go check WatsonService as there may be some error in getting result for Emotion");
        }

        return emotionList;

    }

    public List<SyntaxResult> spitSyntaxResponse() {
        List<SyntaxResult> syntaxResultList = new ArrayList<>();

        try {
            AnalysisResults analysisResults = this.watsonService.connectToWatson();
            syntaxResultList = this.watsonService.parseSyntax(analysisResults);
        } catch (Exception e) {
            logger.warning("If you see this warning, go check WatsonService as there may be some error in getting result for Syntax");
        }

        return syntaxResultList;
    }

    public String getKeywordResponse(int index) {
        String result = "";
        try {
            result = this.watsonService.parseKeyword().get(index);
        } catch (Exception e) {
            logger.info("getKeywordResponse is not working, its probably the index is wrong");
        }
        return result;
    }

    public int getKeywordSize() {
        return this.watsonService.parseKeyword().size();
    }
}
