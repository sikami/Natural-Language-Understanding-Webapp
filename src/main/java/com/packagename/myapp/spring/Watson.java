package com.packagename.myapp.spring;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;

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


    }

}
