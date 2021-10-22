package com.packagename.myapp.spring;


import com.google.gson.*;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.BearerTokenAuthenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.*;

public class WatsonService {

    private WatsonController watsonController;
    private PasswordReader passwordReader;


    public WatsonService(WatsonController watsonController) throws IOException {
        this.watsonController = watsonController;
        this.passwordReader = new PasswordReader();
        this.passwordReader.read("src/main/resources/config");

    }


    //moving this to query
    public static boolean optionChooser(String option) {
        return option.equals("Emotion") || option.equals("Syntax");
    }

    /**
     * This method populates keywords. Important for Query to be declared in WatsonController.
     * Method has to be executed everytime we want to send for keyword to IBM
     *
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

    /**
     * No need to authenticate with IAMAuthenticator no more, use Authenticator class and instantiate BearerTokenAuthenticator(string access token)
     * BearerTokenAuthenticator needs access token which can be accessed via curl:
     * curl -X POST     "https://iam.cloud.ibm.com/identity/token"     -H "content-type: application/x-www-form-urlencoded"     -H "accept: application/json"     -d 'grant_type=urn%3Aibm%3Aparams%3Aoauth%3Agrant-type%3Aapikey&apikey=<API_KEY>' > token.json
     *
     * access token needs to be taken every so often as it expires.
     * more info:
     * https://cloud.ibm.com/docs/key-protect?topic=key-protect-retrieve-access-token
     */
    public AnalysisResults connectToWatson() {

        Authenticator accessTokenRequest = new BearerTokenAuthenticator(passwordReader.getAccessToken());
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2021-08-01", accessTokenRequest);
        service.setServiceUrl(passwordReader.getUrl());
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
            results = service.analyze(parameters)
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

            results = service.analyze(analyzeOptions)
                    .execute()
                    .getResult();

        }

        return results;
    }

    public List<Emotion> parseEmotion(AnalysisResults analysisResults) {
        double sadness;
        double joy;
        double fear;
        double disgust;
        double anger;
        String text;

        List<Emotion> listOfEmotions = new ArrayList<>();
        String result = analysisResults.getEmotion().getTargets().toString();

        JsonArray jsonArray = new JsonParser().parse(result).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {

            try {
                JsonObject jsonObj = new JsonParser().parse(String.valueOf(jsonElement)).getAsJsonObject();
                text = jsonObj.get("text").getAsString();

                JsonObject jsonObjectChildren = new JsonParser().parse(String.valueOf(jsonObj.get("emotion"))).getAsJsonObject();
                anger = jsonObjectChildren.get("anger").getAsDouble();
                disgust = jsonObjectChildren.get("disgust").getAsDouble();
                fear = jsonObjectChildren.get("fear").getAsDouble();
                joy = jsonObjectChildren.get("joy").getAsDouble();
                sadness = jsonObjectChildren.get("sadness").getAsDouble();

                listOfEmotions.add(new Emotion(text,sadness, joy, fear, disgust, anger));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No JsonElement to parse");
            }

        }
        return listOfEmotions;
    }

    public List<SyntaxResult> parseSyntax(AnalysisResults analysisResults) {
        String word;
        String partOfSpeech;

        List<SyntaxResult> syntaxResultList = new ArrayList<>();
        String result = analysisResults.getSyntax().toString();

        JsonArray jsonArray = new JsonParser().parse(result).getAsJsonArray();
        return syntaxResultList;
    }
}
