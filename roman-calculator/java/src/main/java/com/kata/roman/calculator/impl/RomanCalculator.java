package com.kata.roman.calculator.impl;

import com.kata.roman.calculator.itf.IRomanCalculator;
import com.kata.roman.calculator.itf.IRomanNumber;

import java.util.*;

import static com.kata.roman.calculator.impl.RomanNumeral.*;

/**
 * The roman calculator implementation.
 */
public class RomanCalculator implements IRomanCalculator {

    /**
     * The list of Roman numerals in descending order according to their decimal values.
     */
    private static final List<RomanNumeral> ROMAN_NUMERALS_DESCENDING_ORDER = Arrays.stream(RomanNumeral.values())
            .sorted(Comparator.comparing(RomanNumeral::getDecimalValue).reversed())
            .toList();

    /**
     * The map containing all the next lower Roman numbers of each {@link RomanNumeral}.
     */
    private static final Map<RomanNumeral, IRomanNumber> ROMAN_NUMERALS_NEXT_LOWER_VALUE = Map.of(
            M, IRomanNumber.of(C, M),
            D, IRomanNumber.of(C, D),
            C, IRomanNumber.of(X, C),
            L, IRomanNumber.of(X, L),
            X, IRomanNumber.of(I, X),
            V, IRomanNumber.of(I, V),
            I, IRomanNumber.of()
    );

    @Override
    public IRomanNumber add(IRomanNumber first, IRomanNumber second) {
        if (first.getNumerals().isEmpty() || second.getNumerals().isEmpty()) {
            throw new IllegalArgumentException("Cannot add 2 empty roman numerals");
        }

        int firstDecimalValue = getDecimalRepresentationOfRomanNumber(first);
        int secondDecimalValue = getDecimalRepresentationOfRomanNumber(second);

        return getRomanRepresentationOfDecimalNumber(firstDecimalValue + secondDecimalValue);
    }

    /**
     * Get the decimal representation of a Roman number.
     * @param romanNumber the Roman number.
     * @return the decimal representation of a Roman number.
     */
    private int getDecimalRepresentationOfRomanNumber(IRomanNumber romanNumber) {
        int decimalValue = 0;
        int previousIndexDecimalValue = 0;
        int currentIndexDecimalValue;

        for (int index = 0; index < romanNumber.getNumerals().size(); index++) {
            currentIndexDecimalValue = romanNumber.getNumerals().get(index).getDecimalValue();

            if (index > 0) {
                // Subtracting 2 * the previous index decimal value because we would have added it a first time in the previous iteration
                decimalValue += currentIndexDecimalValue > previousIndexDecimalValue
                        ? currentIndexDecimalValue - 2 * previousIndexDecimalValue : currentIndexDecimalValue;
            } else {
                decimalValue += romanNumber.getNumerals().get(index).getDecimalValue();
            }

            previousIndexDecimalValue = currentIndexDecimalValue;
        }

        return decimalValue;
    }

    /**
     * Get the Roman representation of a decimal number.
     *
     * @param decimalNumber the decimal number.
     * @return the Roman representation of the input decimal number.
     */
    private IRomanNumber getRomanRepresentationOfDecimalNumber(int decimalNumber) {

        IRomanNumber romanNumber = IRomanNumber.newInstance();

        int remainingValue = decimalNumber;
        List<RomanNumeral> nextNumerals = new ArrayList<>();

        while (remainingValue > 0) {
            remainingValue = appendNextNumeralsAndGetRemainingValue(remainingValue,
                    nextNumerals);
            nextNumerals.forEach(romanNumber::appendNumeral);
            nextNumerals.clear();
        }

        return romanNumber;
    }

    /**
     * Get the next Roman numerals from a decimal value.
     *
     * @param decimalValue the decimal value.
     * @param nextNumerals the next numerals list that will be mutated to include the new next numerals,
     *                     this is done to avoid creating a new list in memory for every method call.
     * @return the remaining decimal value.
     */
    private int appendNextNumeralsAndGetRemainingValue(int decimalValue,
                                                       List<RomanNumeral> nextNumerals) {
        int remainingValue = decimalValue;
        for (RomanNumeral numeral : ROMAN_NUMERALS_DESCENDING_ORDER) {
            if (isNumeralUsable(remainingValue, numeral)) {
                remainingValue = getRemainingValueAfterAppendingNumber(decimalValue,
                        IRomanNumber.of(numeral), nextNumerals);
                // If the numeral is not usable then a combination with a smaller numeral might be usable
                if (remainingValue == decimalValue) {
                    remainingValue = getRemainingValueAfterAppendingNumber(decimalValue,
                            ROMAN_NUMERALS_NEXT_LOWER_VALUE.get(numeral), nextNumerals);
                }
            }

            if (remainingValue != decimalValue) {
                break;
            }
        }

        return remainingValue;
    }

    /**
     * Get the remaining decimal value after attempting to append a {@link IRomanNumber}.
     *
     * @param decimalValue the decimal value.
     * @param romanNumber  the roman number.
     * @param nextNumerals the next numerals.
     * @return the remaining decimal value.
     */
    private int getRemainingValueAfterAppendingNumber(int decimalValue,
                                                      IRomanNumber romanNumber, List<RomanNumeral> nextNumerals) {
        int decimalRepresentationOfRomanNumber = getDecimalRepresentationOfRomanNumber(romanNumber);
        int factor = decimalValue / decimalRepresentationOfRomanNumber;
        if (factor > 0) {
            for (int index = 0; index < factor; index++) {
                nextNumerals.addAll(romanNumber.getNumerals());
            }
            decimalValue -= decimalRepresentationOfRomanNumber * factor;
        }

        return decimalValue;
    }

    /**
     * Find whether a numeral is usable in the current Roman number.
     *
     * @param remainingValue the decimal value to convert.
     * @param numeral        the numeral.
     * @return true if the numeral is usable, false otherwise.
     */
    private boolean isNumeralUsable(int remainingValue,
                                    RomanNumeral numeral) {
        return remainingValue >= getDecimalRepresentationOfRomanNumber(ROMAN_NUMERALS_NEXT_LOWER_VALUE.get(numeral));
    }
}
