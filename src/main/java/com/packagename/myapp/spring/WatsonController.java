package com.packagename.myapp.spring;

public class WatsonController {
    private Query query;

    public WatsonController() {
    }

    public WatsonController(Query queries) {
        this.query = queries;
    }

    public String getKeyword() {
        return query.getKeyword();
    }

    public String getText() {
        return query.getText();
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }
}
