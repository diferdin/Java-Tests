package com.diferdin.fizzbuzz;

import org.junit.Test;

/**
 * Created by LONADF on 08/06/2016.
 */
public class FizzBuzzStepTwo {

    public String printFizzBuzz(int lowerBound, int upperBound) {
        String result = "";

        if (lowerBound == 0) {
            return result;
        }

        if (lowerBound == upperBound) {
            return checkForDividers(lowerBound);
        }

        for(int i = lowerBound; i < upperBound +1; i++) {
            String space = " ";
            if(i == upperBound) {
                space = "";
            }
            result += checkForDividers(i) + space;
        }

        return result;
    }

    private String checkForDividers(int number) {

        if(containsNumber(number, 3)) {
            return  "lucky";
        }

        if (dividesBy(number, 3) && dividesBy(number, 5)) {
            return "fizzbuzz";
        }

        if (dividesBy(number, 5)) {
            return "buzz";
        }

        if (dividesBy(number, 3)) {
            return "fizz";
        }

        return String.valueOf(number);
    }

    private boolean dividesBy(int number, int divider) {
        return number % divider == 0;
    }

    private boolean containsNumber(int numberToCheck, int numberToBeContained) {
        return String.valueOf(numberToCheck).contains(String.valueOf(numberToBeContained));
    }
}
