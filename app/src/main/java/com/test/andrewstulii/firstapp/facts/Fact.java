package com.test.andrewstulii.firstapp.facts;

/**
 * Created by andrewstulii.
 */
public class Fact {

    private String fact_en;
    private String fact_ru;
    private boolean fact_truth;

    public Fact(String fact_en, String fact_ru, boolean fact_truth) {
        this.fact_en = fact_en;
        this.fact_ru = fact_ru;
        this.fact_truth = fact_truth;
    }

    public String getFact_en() {
        return fact_en;
    }

    public void setFact_en(String fact_en) {
        this.fact_en = fact_en;
    }

    public String getFact_ru() {
        return fact_ru;
    }

    public void setFact_ru(String fact_ru) {
        this.fact_ru = fact_ru;
    }

    public boolean isFact_truth() {
        return fact_truth;
    }

    public void setFact_truth(boolean fact_truth) {
        this.fact_truth = fact_truth;
    }
}
