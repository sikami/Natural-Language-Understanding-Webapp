package com.packagename.myapp.spring;

public class WatsonController {
    private Query query;

    public WatsonController(Query query) {
        this.query = query;
    }

    public String getKeyword() {
        return query.getKeyword();
    }

    public String getText() {
        return query.getText();
    }
}
