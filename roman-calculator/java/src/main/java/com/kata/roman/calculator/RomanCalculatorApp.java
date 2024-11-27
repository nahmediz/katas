package com.kata.roman.calculator;

import com.kata.roman.calculator.impl.RomanCalculator;
import com.kata.roman.calculator.itf.IRomanCalculator;
import com.kata.roman.calculator.itf.IRomanNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Calculator application.
 */
public class RomanCalculatorApp {

    /**
     * The calculator instance.
     */
    private static final IRomanCalculator CALCULATOR = new RomanCalculator();

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RomanCalculatorApp.class);

    /**
     * The main method.
     * @param args the arguments : 2 Strings containing a Roman number each.
     * @throws IllegalArgumentException in case less or more than 2 arguments are supplied or if
     * one of the arguments is not a valid Roman number.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The calculator expects 2 String containing a Roman number each");
        }

        IRomanNumber first = IRomanNumber.parse(args[0]);
        IRomanNumber second = IRomanNumber.parse(args[1]);

        IRomanNumber romanNumber = CALCULATOR.add(first, second);
        LOGGER.info("The sum of {} and {} is : {}", first, second, romanNumber);
    }
}
