package com.diferdin.fizzbuzz;

/**
 * Created by LONADF on 08/06/2016.
 */

public class FizzBuzzStepOne {


    public String printFizzBuzz(int lowerBound, int upperBound) {
        String result = "";

        if (lowerBound == 0) {
            return result;
        }

        if (lowerBound == upperBound) {
            return checkForDividers(lowerBound);
        }

        for(int i = lowerBound; i < upperBound +1; i++) {
            String separator = " ";
            if(i == upperBound) {
                separator = "";
            }
            result += checkForDividers(i) + separator;
        }

        return result;
    }

    private String checkForDividers(int number) {
        String separator = "";
        boolean dividesByThree = number % 3 == 0;
        boolean dividesByFive = number % 5 == 0;

        if (dividesByThree && dividesByFive) {
            return "fizzbuzz";
        }

        if (dividesByFive) {
            return "buzz";
        }

        if (dividesByThree) {
            return "fizz";
        }

        return separator + String.valueOf(number);
    }
}
