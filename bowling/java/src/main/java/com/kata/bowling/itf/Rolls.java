package com.kata.bowling.itf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The rolls' implementation.
 */
class Rolls implements IRolls {

    /**
     * The rolls' scores.
     */
    private final List<IRoundScore> scores = new ArrayList<>();

    @Override
    public IRolls addScore(IRoundScore score) {
        scores.add(score);
        return this;
    }

    @Override
    public IRolls addScoreNTimes(IRoundScore score, int n) {
        for (int i = 0; i < n; i++) {
            addScore(score);
        }
        return this;
    }

    @Override
    public List<IRoundScore> getRoundsScores() {
        return Collections.unmodifiableList(scores);
    }
}
