package org.example.service;

import org.example.model.Match;
import org.example.model.Player;

public class MatchScoreCalculationService {

    public void updateScore(Match match, Player winner) {
        if (match.isFinished()) {
            throw new IllegalStateException("Match is already finished");
        }

        match.incrementScore(winner);

        if (match.isFinished()) {
            match.setWinner(winner);
        }
    }
}