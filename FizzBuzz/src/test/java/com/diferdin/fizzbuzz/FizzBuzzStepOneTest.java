package com.diferdin.fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 08/06/2016.
 */
public class FizzBuzzStepOneTest {

    FizzBuzzStepOne fizzBuzzStepOne;

    @Before
    public void configureTest() {
        fizzBuzzStepOne = new FizzBuzzStepOne();
    }

    @Test
    public void shouldPrintNothingWhenPassedAZeroInterval() {

        String output = fizzBuzzStepOne.printFizzBuzz(0, 0);
        assertEquals("", output);
    }

    @Test
    public void shouldReturnOne() {
        String result = fizzBuzzStepOne.printFizzBuzz(1, 1);

        assertEquals(result, "1");
    }

    @Test
    public void shouldNotPrintForReverseIntervals() {
        String result = fizzBuzzStepOne.printFizzBuzz(3, 1);

        assertEquals("", result);
    }

    @Test
    public void shouldPrintFizzForThree() {
        String result = fizzBuzzStepOne.printFizzBuzz(3,3);

        assertEquals("fizz", result);
    }

    @Test
    public void shouldPrintBuzzForFive() {
        String result = fizzBuzzStepOne.printFizzBuzz(5,5);

        assertEquals("buzz", result);
    }

    @Test
    public void shouldPrintFizzBuzzForFifteen() {
        String result = fizzBuzzStepOne.printFizzBuzz(15,15);

        assertEquals("fizzbuzz", result);
    }

    @Test
    public void shouldPrintCustomStringWithInterval() {
        String expectedResult = "1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz";
        String result = fizzBuzzStepOne.printFizzBuzz(1, 20);

        assertEquals(expectedResult, result);
    }
}
