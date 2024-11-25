package com.kata.bowling.itf;

import java.util.List;

/**
 * The interface representing the rolls of a player in a single game.
 */
public interface IRolls {

    /**
     * Add a score to the rolls.
     * @param score the score to add.
     * @return the rolls instance.
     */
    IRolls addScore(IRoundScore score);

    /**
     * Add a given score n times to the rolls.
     * @param score the score to add.
     * @param n the number of times the score will be added.
     * @return the rolls' instance.
     */
    IRolls addScoreNTimes(IRoundScore score, int n);

    /**
     * Get a new {@link IRolls} instance.
     * @return a new {@link IRolls} instance.
     */
    static IRolls getNewInstance() {
        return new Rolls();
    }

    /**
     * Get the rolls' rounds scores.
     * @return an immutable view list of the rolls' scores.
     */
    List<IRoundScore> getRoundsScores();
}
