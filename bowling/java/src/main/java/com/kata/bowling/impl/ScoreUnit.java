package com.kata.bowling.impl;

/**
 * The enumeration of all possible scores in one throw.
 */
public enum ScoreUnit {

    ZERO (0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), STRIKE(10);

    /**
     * The score's value.
     */
    private final int value;

    /**
     * The constructor.
     * @param value the score unit's value.
     */
    ScoreUnit(int value) {
        this.value = value;
    }

    /**
     * The value's getter.
     * @return the score's value.
     */
    public int getValue() {
        return value;
    }
}
