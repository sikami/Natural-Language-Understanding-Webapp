package com.packagename.myapp.spring;

public class SyntaxResult {
    private String word;
    private String partOfSpeech;

    public SyntaxResult(String word, String partOfSpeech) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
    }

    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String toString() {
        return "Word: " + getWord() + "\n"
                + "Part of speech: " + getPartOfSpeech() + "\n\n";
    }
}
