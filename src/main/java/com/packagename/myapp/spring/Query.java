package com.packagename.myapp.spring;

public class Query {
    private String text;
    private String keywords;

    public Query() {
    }

    public Query(String text, String keywords) {
        this.text = text;
        this.keywords = keywords;
    }

    public Query(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeyword() {
        return this.keywords;
    }

    public String getText() {
        return this.text;
    }
}
