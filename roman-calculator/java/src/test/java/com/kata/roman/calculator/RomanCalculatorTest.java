package com.kata.roman.calculator;

import com.kata.roman.calculator.impl.RomanCalculator;
import com.kata.roman.calculator.impl.RomanNumeral;
import com.kata.roman.calculator.itf.IRomanCalculator;
import com.kata.roman.calculator.itf.IRomanNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.kata.roman.calculator.impl.RomanNumeral.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RomanCalculatorTest {

    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("Should get the right result when adding roman numbers")
    void testAddNumbersWithRomanCalculator(IRomanNumber first, IRomanNumber second,
                        List<RomanNumeral> expectedRomanNumerals) {
        // Given
        IRomanCalculator calculator = new RomanCalculator();

        // When
        IRomanNumber resultOne = calculator.add(first, second);
        IRomanNumber resultTwo = calculator.add(second, first);

        // Then
        assertArrayEquals(resultOne.getNumerals().toArray(), expectedRomanNumerals.toArray());
        assertArrayEquals(resultTwo.getNumerals().toArray(), expectedRomanNumerals.toArray());
    }

    @Test
    void testAddEmptyNumber() {
        // Given
        IRomanCalculator calculator = new RomanCalculator();
        IRomanNumber romanNumber1 = IRomanNumber.parse("IV");
        IRomanNumber romanNumber2 = IRomanNumber.newInstance();

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.add(romanNumber1, romanNumber2));
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(IRomanNumber.of(I), IRomanNumber.of(I), List.of(I, I)),
                Arguments.of(IRomanNumber.of(X, I, V), IRomanNumber.of(L, X), List.of(L, X, X, I, V)),
                Arguments.of(IRomanNumber.of(X, X), IRomanNumber.of(I, I), List.of(X, X, I, I)),
                Arguments.of(IRomanNumber.of(D, C), IRomanNumber.of(D), List.of(M, C)),
                Arguments.of(IRomanNumber.of(I, I, I), IRomanNumber.of(I), List.of(I, V)),
                Arguments.of(IRomanNumber.of(I, V), IRomanNumber.of(I, I, I), List.of(V, I, I))
        );
    }
}
