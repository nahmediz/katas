package com.kata.roman.calculator.itf;

/**
 * The roman calculator interface.
 */
public interface IRomanCalculator {

    /**
     * Add two roman numbers.
     * @param first the first number.
     * @param second the second number.
     * @return the result.
     * @throws IllegalArgumentException in case one of the 2 numbers contains no numeral.
     */
    IRomanNumber add(IRomanNumber first, IRomanNumber second);
}
