package com.diferdin.fizzbuzz;

/**
 * Created by LONADF on 08/06/2016.
 */

public class FizzBuzzStepThree {
    public String printFizzBuzz(int lowerBound, int upperBound) {
        String result = "";

        if(lowerBound > 0 && lowerBound <= upperBound) {

            if (lowerBound == upperBound) {
                result = checkForDividers(lowerBound);
            } else {
                for (int i = lowerBound; i < upperBound + 1; i++) {
                    String space = " ";
                    if (i == upperBound) {
                        space = "";
                    }
                    result += checkForDividers(i) + space;
                }
            }
        }

        return result+"\n"+countOccurrences(result);
    }

    private String countOccurrences(String result) {
        int fizzCounter = 0;
        int buzzCounter = 0;
        int fizzBuzzCounter = 0;
        int luckyCounter = 0;
        int integerCounter = 0;

        if("".equals(result)) {
            return formatString(0, 0, 0, 0, 0);
        }

        String[] stringsArray = result.split(" ");

        for (String stringElement : stringsArray) {
            if ("fizz".equals(stringElement)) {
                fizzCounter++;
                continue;
            }

            if ("buzz".equals(stringElement)) {
                buzzCounter++;
                continue;
            }

            if ("fizzbuzz".equals(stringElement)) {
                fizzBuzzCounter++;
                continue;
            }

            if ("lucky".equals(stringElement)) {
                luckyCounter++;
                continue;
            }

            integerCounter++;
        }

        return formatString(fizzCounter, buzzCounter, fizzBuzzCounter, luckyCounter, integerCounter);
    }

    private String formatString(int fizzCounter,
                                int buzzCounter, int fizzBuzzCounter,
                                int luckyCounter,
                                int integerCounter) {
        return "fizz: "+fizzCounter         +"\n"+
                "buzz: "+buzzCounter        +"\n"+
                "fizzbuzz: "+fizzBuzzCounter+"\n"+
                "lucky: "+luckyCounter      +"\n"+
                "integer: "+integerCounter;
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
