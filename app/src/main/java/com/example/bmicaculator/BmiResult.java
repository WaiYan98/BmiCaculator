package com.example.bmicaculator;

public class BmiResult {

    private double bmiNumResult;
    private Result bmiTxtResult;
    private String bmiDescription;

    public BmiResult(double bmiNumResult, Result bmiTxtResult, String bmiDescription) {
        this.bmiNumResult = bmiNumResult;
        this.bmiTxtResult = bmiTxtResult;
        this.bmiDescription = bmiDescription;
    }

    public double getBmiNumResult() {
        return bmiNumResult;
    }

    public void setBmiNumResult(double bmiNumResult) {
        this.bmiNumResult = bmiNumResult;
    }

    public Result getBmiTxtResult() {
        return bmiTxtResult;
    }

    public void setBmiTxtResult(Result bmiTxtResult) {
        this.bmiTxtResult = bmiTxtResult;
    }

    public String getBmiDescription() {
        return bmiDescription;
    }

    public void setBmiDescription(String bmiDescription) {
        this.bmiDescription = bmiDescription;
    }

    @Override
    public String toString() {
        return "BmiResult{" +
                "bmiNumResult=" + bmiNumResult +
                ", bmiTxtResult='" + bmiTxtResult + '\'' +
                ", bmiDescription='" + bmiDescription + '\'' +
                '}';
    }
}
