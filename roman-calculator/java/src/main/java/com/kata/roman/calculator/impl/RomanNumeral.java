package com.kata.roman.calculator.impl;

/**
 * The roman numerals.
 */
public enum RomanNumeral {

    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    /**
     * The decimal value of the Roman numeral.
     */
    private final int decimalValue;

    /**
     * The constructor.
     *
     * @param decimalValue the decimal value of the Roman numeral.
     */
    RomanNumeral(int decimalValue) {
        this.decimalValue = decimalValue;
    }

    /**
     * Get the decimal value of the Roman numeral.
     * @return the decimal value of the Roman numeral.
     */
    public int getDecimalValue() {
        return decimalValue;
    }
}
