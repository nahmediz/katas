package com.kata.bowling.itf;

import com.kata.bowling.impl.ScoreUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The score implementation.
 */
class RoundScore implements IRoundScore {

    /**
     * The score's score units.
     */
    private final List<ScoreUnit> scoreUnits = new ArrayList<>();

    @Override
    public IRoundScore addScoreUnit(ScoreUnit scoreUnit) {
        scoreUnits.add(scoreUnit);
        return this;
    }

    @Override
    public List<ScoreUnit> getScoreUnits() {
        return Collections.unmodifiableList(scoreUnits);
    }
}
