package com.kata.bowling;

import com.kata.bowling.impl.ScoreCalculator;
import com.kata.bowling.itf.IRolls;
import com.kata.bowling.itf.IRoundScore;
import com.kata.bowling.itf.IScoreCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.kata.bowling.impl.ScoreUnit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The score calculator test class.
 */
class ScoreUnitCalculatorTest {

    private final IScoreCalculator calculator = new ScoreCalculator();

    @ParameterizedTest
    @MethodSource("getRolls")
    @DisplayName("""
            Should get a score of 300 if the player scores 12 strikes,
            Should get a score of 90 if the player scores 9 pins in each throw,
            Should get a score of 150 if the player gets all spares after hitting 5 pins
            """)
    void testAllStrikes(IRolls rolls, int expectedScore) {
        // When
        int score = calculator.calculateScore(rolls);

        // Then
        assertEquals(expectedScore, score);
    }

    public static Stream<Arguments> getRolls() {
        IRolls rollsOfStrikes = IRolls.getNewInstance().
                addScoreNTimes(IRoundScore.strike(),  12);

        IRolls rollsOfNines = IRolls.getNewInstance()
                .addScoreNTimes(IRoundScore.scoreOf(NINE, ZERO), 10);

        IRolls rollsOfSpares = IRolls.getNewInstance()
                .addScoreNTimes(IRoundScore.scoreOf(FIVE, FIVE), 10)
                .addScore(IRoundScore.scoreOf(FIVE));

        return Stream.of(Arguments.of(rollsOfStrikes, 300),
                Arguments.of(rollsOfNines, 90),
                Arguments.of(rollsOfSpares, 150));
    }
}
