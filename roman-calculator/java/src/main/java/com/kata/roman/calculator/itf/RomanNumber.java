package com.kata.roman.calculator.itf;

import com.kata.roman.calculator.impl.RomanNumeral;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * The roman number implementation.
 */
class RomanNumber implements IRomanNumber {

    /**
     * The number's literals.
     */
    private final List<RomanNumeral> romanNumerals = new ArrayList<>();

    @Override
    public void appendNumeral(RomanNumeral romanNumeral) {
        romanNumerals.add(romanNumeral);
    }

    @Override
    public List<RomanNumeral> getNumerals() {
        return Collections.unmodifiableList(romanNumerals);
    }

    @Override
    public String toString() {
        return romanNumerals.stream()
                .map(RomanNumeral::toString)
                .collect(Collectors.joining());
    }
}
