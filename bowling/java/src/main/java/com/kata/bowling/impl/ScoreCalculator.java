package com.kata.bowling.impl;

import com.kata.bowling.itf.IRolls;
import com.kata.bowling.itf.IRoundScore;
import com.kata.bowling.itf.IScoreCalculator;

import java.util.List;

import static com.kata.bowling.impl.ScoreUnit.STRIKE;

/**
 * The score calculator implementation.
 */
public class ScoreCalculator implements IScoreCalculator {

    @Override
    public int calculateScore(IRolls rolls) {
        List<IRoundScore> roundsScores = rolls.getRoundsScores();

        int totalScore = 0;
        for (int i = 0; i < roundsScores.size() && i < 10; i++) {
            final List<IRoundScore> nextRoundsScores = roundsScores.subList(i + 1, roundsScores.size());
            totalScore += getRoundTotalScore((roundsScores.get(i)), nextRoundsScores);
        }

        return totalScore;
    }

    /**
     * Get the total score of a score round based on the next rounds' scores.
     * @param roundScore the round score.
     * @param nextRoundsScores the next rounds' scores.
     * @return the current round total score.
     */
    private int getRoundTotalScore(IRoundScore roundScore, List<IRoundScore> nextRoundsScores) {
        int sumOfRoundPins = roundScore.getScoreUnits().stream()
                .mapToInt(ScoreUnit::getValue)
                .sum();

        if (roundScore.getScoreUnits().contains(STRIKE)) {
            int nextTwoThrowsPins = getSumOfNFirstThrowPins(nextRoundsScores, 2);

            return 10 + nextTwoThrowsPins;
        } else if (sumOfRoundPins == 10) {
            int nextThrowPins = getSumOfNFirstThrowPins(nextRoundsScores, 1);

            return 10 + nextThrowPins;
        } else {
            return sumOfRoundPins;
        }
    }

    /**
     * Get the sum of the number of pins in the first n throws.
     * @param roundsScores the rounds' scores.
     * @param numberOfThrows the number of throws to consider.
     * @return the sum of the number of pins hit by the n first throws.
     */
    private int getSumOfNFirstThrowPins(List<IRoundScore> roundsScores, int numberOfThrows) {
        return roundsScores.stream()
                .flatMap(score -> score.getScoreUnits().stream())
                .limit(numberOfThrows)
                .mapToInt(ScoreUnit::getValue)
                .sum();
    }
}
