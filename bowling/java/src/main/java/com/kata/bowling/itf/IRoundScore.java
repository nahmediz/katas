package com.kata.bowling.itf;

import com.kata.bowling.impl.ScoreUnit;

import java.util.List;

import static com.kata.bowling.impl.ScoreUnit.STRIKE;

/**
 * The score interface.
 */
public interface IRoundScore {

    /**
     * Add a score unit to the current score.
     * @param scoreUnit the score unit to add.
     * @return the {@link IRoundScore} instance.
     */
    IRoundScore addScoreUnit(ScoreUnit scoreUnit);

    /**
     * Get a strike score.
     * @return a strike score.
     */
    static IRoundScore strike() {
        return new RoundScore().addScoreUnit(STRIKE);
    }

    /**
     * Get a new {@link IRoundScore instance} based on a score unit.
     *
     * @return a new {@link IRoundScore instance} based on a score unit.
     */
    static IRoundScore scoreOf(ScoreUnit... scoreUnits) {
        IRoundScore roundScore = new RoundScore();
        for (ScoreUnit scoreUnit : scoreUnits) {
            roundScore.addScoreUnit(scoreUnit);
        }
        return roundScore;
    }

    /**
     * Get the score units.
     * @return an immutable list of the score units.
     */
    List<ScoreUnit> getScoreUnits();
}
