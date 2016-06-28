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
        boolean dividesByThree = number % 3 == 0;
        boolean dividesByFive = number % 5 == 0;

        if (dividesByThree && dividesByFive) {
            return "fizzbuzz";
        }

        if (dividesByFive) {
            return "buzz";
        }

        if(String.valueOf(number).contains("3")) {
            return "lucky";
        }

        if (dividesByThree) {
            return "fizz";
        }

        return String.valueOf(number);
    }
}
