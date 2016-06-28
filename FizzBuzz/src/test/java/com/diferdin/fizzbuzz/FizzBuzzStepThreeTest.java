package com.diferdin.fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 08/06/2016.
 */
public class FizzBuzzStepThreeTest {

    private FizzBuzzStepThree fizzBuzzStepThree;

    @Before
    public void configureTest() {
        fizzBuzzStepThree = new FizzBuzzStepThree();
    }

    @Test
    public void shouldPrintNothingWhenPassedAZeroInterval() {
        String expectedResult = "\nfizz: 0\nbuzz: 0\nfizzbuzz: 0\nlucky: 0\ninteger: 0";
        String output = fizzBuzzStepThree.printFizzBuzz(0, 0);
        assertEquals(expectedResult, output);
    }

    @Test
    public void shouldReturnOne() {
        String expectedResult = "1\nfizz: 0\nbuzz: 0\nfizzbuzz: 0\nlucky: 0\ninteger: 1";
        String result = fizzBuzzStepThree.printFizzBuzz(1, 1);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotPrintForReverseIntervals() {
        String expectedResult = "\nfizz: 0\nbuzz: 0\nfizzbuzz: 0\nlucky: 0\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(3, 1);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintLucky() {
        String expectedResult = "lucky\nfizz: 0\nbuzz: 0\nfizzbuzz: 0\nlucky: 1\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(3,3);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintFizz() {
        String expectedResult = "fizz\nfizz: 1\nbuzz: 0\nfizzbuzz: 0\nlucky: 0\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(12, 12);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintFizzLucky() {
        String expectedResult = "fizz lucky\nfizz: 1\nbuzz: 0\nfizzbuzz: 0\nlucky: 1\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(12, 13);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintBuzz() {
        String expectedResult = "buzz\nfizz: 0\nbuzz: 1\nfizzbuzz: 0\nlucky: 0\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(5,5);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintFizzBuzz() {
        String expectedResult = "fizzbuzz\nfizz: 0\nbuzz: 0\nfizzbuzz: 1\nlucky: 0\ninteger: 0";
        String result = fizzBuzzStepThree.printFizzBuzz(15,15);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldPrintCustomStringWithInterval() {
        String expectedResult = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz\n" +
                "fizz: 4\n" +
                "buzz: 3\n" +
                "fizzbuzz: 1\n" +
                "lucky: 2\n" +
                "integer: 10";
        String result = fizzBuzzStepThree.printFizzBuzz(1, 20);

        assertEquals(expectedResult, result);
    }
}
