package com.kata.roman.calculator.itf;

import com.kata.roman.calculator.impl.RomanNumeral;

import java.util.List;

/**
 * The roman number interface.
 */
public interface IRomanNumber {

    /**
     * Append a numeral at the end of the number.
     *
     * @param romanNumeral the roman numeral.
     */
    void appendNumeral(RomanNumeral romanNumeral);

    /**
     * Get the number's numerals.
     * @return an immutable list of the number's numerals.
     */
    List<RomanNumeral> getNumerals();

    /**
     * Get a number based on a list of numerals.
     *
     * @param romanNumerals the numerals.
     * @return the roman number instance.
     */
    static IRomanNumber of(RomanNumeral... romanNumerals) {
        IRomanNumber romanNumber = new RomanNumber();
        for (RomanNumeral romanNumeral : romanNumerals) {
            romanNumber.appendNumeral(romanNumeral);
        }
        return romanNumber;
    }

    /**
     * Get a new instance of a {@link IRomanNumber}.
     * @return a new instance of a {@link IRomanNumber}.
     */
    static IRomanNumber newInstance() {
        return new RomanNumber();
    }

    /**
     * Parse a String into a {@link IRomanNumber} instance.
     * @param number the String containing the Roman number's numerals.
     * @return the {@link IRomanNumber} instance.
     * @throws IllegalArgumentException in case one of the String characters doesn't correspond
     * to a Roman numeral.
     */
    static IRomanNumber parse(String number) {
        IRomanNumber romanNumber = new RomanNumber();
        for (int index = 0; index < number.length(); index++) {
            String currentCharacter = number.substring(index, index + 1);
            try {
                RomanNumeral numeral = RomanNumeral.valueOf(currentCharacter);
                romanNumber.appendNumeral(numeral);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("The character %s is not a Roman numeral",
                        currentCharacter), e);
            }
        }

        return romanNumber;
    }
}
