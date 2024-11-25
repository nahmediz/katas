package com.kata.bowling.itf;

/**
 * The interface defining methods for calculating bowling players' scores.
 */
public interface IScoreCalculator {

    /**
     * Calculate the total score for a given player's rolls.
     * @param rolls the player's rolls.
     * @return the total score.
     */
    int calculateScore(IRolls rolls);
}
