package com.packagename.myapp.spring;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;

public class Watson {
    private PasswordReader passwordReader;
    private IamAuthenticator authenticator;
    private NaturalLanguageUnderstanding naturalLanguageUnderstanding;

    public Watson() {
        this.passwordReader = new PasswordReader();
        this.authenticator = new IamAuthenticator(passwordReader.getApiKey());
        this.naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2021-08-01", authenticator);
    }

    //if emotion
    public void connectToWatson(WatsonController watsonController) {
        naturalLanguageUnderstanding.setServiceUrl(passwordReader.getUrl());

        if (watsonController.getQuery().getOption().contains("Syntax")) {
            SyntaxOptionsTokens syntaxOptionsTokens = new SyntaxOptionsTokens.Builder().partOfSpeech(true)
                    .lemma(true).build();

            SyntaxOptions syntaxOptions = new SyntaxOptions.Builder().tokens(syntaxOptionsTokens).sentences(true).build();
            Features features = new Features.Builder().syntax(syntaxOptions).build();
            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(watsonController.getText())
                    .features(features)
                    .build();
            AnalysisResults results = naturalLanguageUnderstanding.analyze(parameters)
                    .execute()
                    .getResult();

            //result is displayed in AnalysisResults
            System.out.println(results);

        } else if (watsonController.getQuery().getOption().contains("Emotion")) {

        }

    }

}
