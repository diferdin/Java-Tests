package com.diferdin.fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 08/06/2016.
 */
public class FizzBuzzStepTwoTest {

    FizzBuzzStepTwo fizzBuzzStepTwo;

    @Before
    public void configureTest() {
        fizzBuzzStepTwo = new FizzBuzzStepTwo();
    }



    @Test
    public void shouldPrintNothingWhenPassedAZeroInterval() {

        String output = fizzBuzzStepTwo.printFizzBuzz(0, 0);
        assertEquals("", output);
    }

    @Test
    public void shouldPrintLucky() {
        String output = fizzBuzzStepTwo.printFizzBuzz(30, 30);
        assertEquals("lucky", output);

        String result = fizzBuzzStepTwo.printFizzBuzz(3,3);

        assertEquals("lucky", result);
    }

    @Test
    public void shouldReturnOne() {
        String result = fizzBuzzStepTwo.printFizzBuzz(1, 1);

        assertEquals(result, "1");
    }

    @Test
    public void shouldNotPrintForReverseIntervals() {
        String result = fizzBuzzStepTwo.printFizzBuzz(3, 1);

        assertEquals("", result);
    }

    @Test
    public void shouldPrintFizz() {
        String result = fizzBuzzStepTwo.printFizzBuzz(12, 12);
        assertEquals("fizz", result);
    }

    @Test
    public void shouldPrintFizzLucky() {
        String result = fizzBuzzStepTwo.printFizzBuzz(12, 13);
        assertEquals("fizz lucky", result);
    }

    @Test
    public void shouldPrintBuzz() {
        String result = fizzBuzzStepTwo.printFizzBuzz(5,5);

        assertEquals("buzz", result);
    }

    @Test
    public void shouldPrintFizzBuzz() {
        String result = fizzBuzzStepTwo.printFizzBuzz(15,15);

        assertEquals("fizzbuzz", result);
    }

    @Test
    public void shouldPrintCustomStringWithInterval() {
        String expectedResult = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        String result = fizzBuzzStepTwo.printFizzBuzz(1, 20);

        assertEquals(expectedResult, result);
    }

    @Test
    public void print() {
        String result = fizzBuzzStepTwo.printFizzBuzz(30, 30);
        System.out.println(result);
    }
}
