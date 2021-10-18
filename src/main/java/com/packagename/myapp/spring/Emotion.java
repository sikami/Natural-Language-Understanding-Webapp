package com.packagename.myapp.spring;

public class Emotion {

    private String word;
    private Double sadness;
    private Double joy;
    private Double fear;
    private Double disgust;
    private Double anger;

    public Emotion(String word, Double sadness, Double joy, Double fear, Double disgust, Double anger) {
        this.word = word;
        this.sadness = sadness;
        this.joy = joy;
        this.fear = fear;
        this.disgust = disgust;
        this.anger = anger;
    }

    public String getWord() {
        return word;
    }

    public Double getSadness() {
        return sadness;
    }

    public Double getJoy() {
        return joy;
    }

    public Double getFear() {
        return fear;
    }

    public Double getDisgust() {
        return disgust;
    }

    public Double getAnger() {
        return anger;
    }

    public String toString() {
        return "Word: " + getWord() + "\n"
                + "Sadness: " + getSadness() + "\n"
                + "Joy: " + getJoy() + "\n"
                + "Fear: " + getFear() + "\n"
                + "Disgust: " + getDisgust() + "\n"
                + "Anger: " + getAnger() + "\n\n";
    }
}
