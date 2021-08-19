package com.packagename.myapp.spring;

public class EmotionResult {

    private double sadness;
    private double joy;
    private double fear;
    private double disgust;
    private double anger;

    public EmotionResult(double sadness, double joy, double fear, double disgust, double anger) {
        this.sadness = sadness;
        this.joy = joy;
        this.fear = fear;
        this.disgust = disgust;
        this.anger = anger;
    }

    public double getSadness() {
        return sadness;
    }

    public double getJoy() {
        return joy;
    }

    public double getFear() {
        return fear;
    }

    public double getDisgust() {
        return disgust;
    }

    public double getAnger() {
        return anger;
    }
}
