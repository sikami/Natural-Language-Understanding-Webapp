package com.packagename.myapp.spring;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WatsonService {

    private WatsonController watsonController;
    private PasswordReader passwordReader;
    private IamAuthenticator authenticator;
    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public WatsonService(WatsonController watsonController) throws IOException {
        this.watsonController = watsonController;
        this.passwordReader = new PasswordReader();
        this.passwordReader.read("src/main/resources/config");
        this.authenticator = new IamAuthenticator(passwordReader.getApiKey());
        this.naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2021-08-01", authenticator);
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

    public AnalysisResults connectToWatson() {
        naturalLanguageUnderstanding.setServiceUrl(passwordReader.getUrl());
        AnalysisResults results = null;

        if (watsonController.getQuery().getOption().contains("Syntax")) {
            SyntaxOptionsTokens syntaxOptionsTokens = new SyntaxOptionsTokens.Builder().partOfSpeech(true)
                    .lemma(true).build();

            SyntaxOptions syntaxOptions = new SyntaxOptions.Builder().tokens(syntaxOptionsTokens).sentences(true).build();
            Features features = new Features.Builder().syntax(syntaxOptions).build();
            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(watsonController.getText())
                    .features(features)
                    .build();
            results = naturalLanguageUnderstanding.analyze(parameters)
                    .execute()
                    .getResult();

            //result is displayed in AnalysisResults
            System.out.println(results);

        } else if (watsonController.getQuery().getOption().contains("Emotion")) {
            List<String> targets = parseKeyword();
            EmotionOptions emotionOptions = new EmotionOptions.Builder()
                    .targets(targets)
                    .build();

            Features features = new Features.Builder()
                    .emotion(emotionOptions)
                    .build();

            AnalyzeOptions analyzeOptions = new AnalyzeOptions.Builder()
                    .text(watsonController.getQuery().getText())
                    .features(features)
                    .build();

            results = naturalLanguageUnderstanding.analyze(analyzeOptions)
                    .execute()
                    .getResult();

        }

        return results;
    }

}
