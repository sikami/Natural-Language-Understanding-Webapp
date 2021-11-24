package com.packagename.myapp.spring;


import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.BearerTokenAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WatsonService {

    private Query query;
    private static Logger logger = Logger.getLogger(WatsonService.class.getName());

    private PasswordReader passwordReader;

    @Autowired
    public WatsonService(Query query) throws IOException {
        this.query = query;
        this.passwordReader = new PasswordReader();
        this.passwordReader.read("src/main/resources/config");

    }

    /**
     * This method populates keywords.
     * Method has to be executed everytime we want to send for keyword to IBM
     *
     */
    public List<String> parseKeyword() {
        List<String> keywords = new ArrayList<>();

        try {
            String keys = query.getKeyword();
            String[] keyword = keys.split(",");

            for (String word: keyword) {
                keywords.add(word.trim().toLowerCase());
            }

        } catch (Exception e) {
            logger.warning("Keywords cannot be parsed.");
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

        AnalysisResults results = null;
        try {
            Authenticator accessTokenRequest = new BearerTokenAuthenticator(passwordReader.getAccessToken());
            NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2021-08-01", accessTokenRequest);
            service.setServiceUrl(passwordReader.getUrl());
            

            if (query.getOption().contains("Syntax")) {
                SyntaxOptionsTokens syntaxOptionsTokens = new SyntaxOptionsTokens.Builder().partOfSpeech(true)
                        .lemma(true).build();

                SyntaxOptions syntaxOptions = new SyntaxOptions.Builder().tokens(syntaxOptionsTokens).sentences(true).build();
                Features features = new Features.Builder().syntax(syntaxOptions).build();
                AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                        .text(query.getText())
                        .features(features)
                        .build();
                results = service.analyze(parameters)
                        .execute()
                        .getResult();

            } else if (query.getOption().contains("Emotion")) {
                List<String> targets = parseKeyword();

                EmotionOptions emotionOptions = new EmotionOptions.Builder()
                        .targets(targets)
                        .build();

                Features features = new Features.Builder()
                        .emotion(emotionOptions)
                        .build();

                AnalyzeOptions analyzeOptions = new AnalyzeOptions.Builder()
                        .text(query.getText())
                        .features(features)
                        .build();

                results = service.analyze(analyzeOptions)
                        .execute()
                        .getResult();

                return results;
            }

        } catch (Exception e) {
            logger.severe("Warning: Authentication does not work. It may be caused by expired or wrongly inputted Auth Token.");
        }
        
        return results;

    }

    //TODO why not use the existing class from Watson instead of creating my own class to contain the result
    //TODO parseEmotion needs to have parse Keywords as keywords will be displayed in the result
    public List<Emotion> parseEmotion(AnalysisResults analysisResults) {
        List<Emotion> listOfEmotions = new ArrayList<>();

        try {
            List<TargetedEmotionResults> targets = analysisResults.getEmotion().getTargets();

            for (TargetedEmotionResults targetedEmotionResults: targets) {
                listOfEmotions.add(new Emotion(targetedEmotionResults.getText(), targetedEmotionResults.getEmotion().getSadness(),
                        targetedEmotionResults.getEmotion().getJoy(), targetedEmotionResults.getEmotion().getFear(),
                        targetedEmotionResults.getEmotion().getDisgust(), targetedEmotionResults.getEmotion().getAnger()));
            }

        } catch (Exception e) {
            logger.warning("Emotion result cannot be parsed from the result.");
        }

        return listOfEmotions;
    }

    public List<SyntaxResult> parseSyntax(AnalysisResults analysisResults) {
        List<SyntaxResult> syntaxResultList = new ArrayList<>();

        try {
            List<TokenResult> results = analysisResults.getSyntax().getTokens();

            for (TokenResult tokenResult : results) {
                syntaxResultList.add(new SyntaxResult(tokenResult.getText(), tokenResult.getPartOfSpeech()));
            }

        } catch (Exception e) {
            logger.warning("Syntax result cannot be parsed.");
        }

        return syntaxResultList;
    }


}
