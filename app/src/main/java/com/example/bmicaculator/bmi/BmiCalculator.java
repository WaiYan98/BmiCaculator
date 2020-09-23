package com.example.bmicaculator.bmi;

import com.example.bmicaculator.BmiResult;
import com.example.bmicaculator.Gender;
import com.example.bmicaculator.Result;

import java.text.DecimalFormat;

public class BmiCalculator {

    private static final double INCH_IN_METER = 0.0254;


    public static BmiResult calculateBmi(int height, double weight) {
        double finalHeight = height * INCH_IN_METER;
        DecimalFormat dcFormat = new DecimalFormat("0.00");
        String bmiStr = dcFormat.format(weight / (Math.pow(finalHeight, 2)));
        double bmi = Double.parseDouble(bmiStr);
        Result bmiTextResult = getBmiTextResult(bmi);
        String descriptionText = getDescriptionText(bmiTextResult);

        BmiResult bmiResult = new BmiResult(bmi, bmiTextResult, descriptionText);
        return bmiResult;
    }

    private static Result getBmiTextResult(double bmi) {

        Result bmiTextResult;

        if (bmi >= 18.5) {
            if (bmi <= 24.9) {
                bmiTextResult = Result.NORMAL_WEIGHT;
            } else if (bmi <= 29.9) {
                bmiTextResult = Result.OVER_WEIGHT;
            } else if (bmi <= 34.9) {
                bmiTextResult = Result.OBESITY_1;
            } else if (bmi <= 39.9) {
                bmiTextResult = Result.OBESITY_2;
            } else {
                bmiTextResult = Result.EXTREME_OBESITY;
            }
        } else {
            bmiTextResult = Result.UNDER_WEIGHT;
        }

        return bmiTextResult;
    }

    private static String getDescriptionText(Result bmiTextResult) {

        String descriptionText = "";

        switch (bmiTextResult) {
            case NORMAL_WEIGHT:
                descriptionText = "You have a normal body weight";
                break;
            case OVER_WEIGHT:
                descriptionText = "You have an over body weight";
                break;
            case OBESITY_1:
                descriptionText = "You have an obesity 1 body weight";
                break;
            case OBESITY_2:
                descriptionText = "You have an obesity 2 body weight";
                break;
            case EXTREME_OBESITY:
                descriptionText = "You have an extreme obesity body weight";
                break;
            case UNDER_WEIGHT:
                descriptionText = "You have an under body weight";
                break;
        }
        return descriptionText;
    }
}
