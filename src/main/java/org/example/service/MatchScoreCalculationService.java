package org.example.service;

import org.example.model.Match;
import org.example.model.MatchScoreModel;
import org.example.model.Player;

public class MatchScoreCalculationService {

    public void incrementScore(MatchScoreModel matchScoreModel, Player player) {
        if (player.equals(matchScoreModel.getMatch().getPlayer1())) {
            matchScoreModel.setPointsPlayer1(matchScoreModel.getPointsPlayer1() + 1);
        } else if (player.equals(matchScoreModel.getMatch().getPlayer2())) {
            matchScoreModel.setPointsPlayer2(matchScoreModel.getPointsPlayer2() + 1);
        }
        checkGameWin(matchScoreModel);
    }

    private void checkGameWin(MatchScoreModel matchScoreModel) {
        int p1Points = matchScoreModel.getPointsPlayer1();
        int p2Points = matchScoreModel.getPointsPlayer2();

        if ((p1Points >= 4 && p1Points - p2Points >= 2) || (p2Points >= 4 && p2Points - p1Points >= 2)) {
            if (p1Points > p2Points) {
                matchScoreModel.setGamesPlayer1(matchScoreModel.getGamesPlayer1() + 1);
            } else {
                matchScoreModel.setGamesPlayer2(matchScoreModel.getGamesPlayer2() + 1);
            }
            matchScoreModel.setPointsPlayer1(0);
            matchScoreModel.setPointsPlayer2(0);
            checkSetWin(matchScoreModel);
        }
    }

    private void checkSetWin(MatchScoreModel matchScoreModel) {
        int p1Games = matchScoreModel.getGamesPlayer1();
        int p2Games = matchScoreModel.getGamesPlayer2();

        if ((p1Games >= 6 && p1Games - p2Games >= 2) || (p2Games >= 6 && p2Games - p1Games >= 2)) {
            if (p1Games > p2Games) {
                matchScoreModel.setSetsPlayer1(matchScoreModel.getSetsPlayer1() + 1);
            } else {
                matchScoreModel.setSetsPlayer2(matchScoreModel.getSetsPlayer2() + 1);
            }
            matchScoreModel.setGamesPlayer1(0);
            matchScoreModel.setGamesPlayer2(0);
            checkMatchWin(matchScoreModel);
        }
    }

    private void checkMatchWin(MatchScoreModel matchScoreModel) {
        int p1Sets = matchScoreModel.getSetsPlayer1();
        int p2Sets = matchScoreModel.getSetsPlayer2();
        Match match = matchScoreModel.getMatch();

        if (p1Sets == 2) {
            match.setWinner(match.getPlayer1());
        } else if (p2Sets == 2) {
            match.setWinner(match.getPlayer2());
        }
    }
}
