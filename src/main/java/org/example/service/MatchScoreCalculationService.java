package org.example.service;

import org.example.model.Match;
import org.example.model.MatchScoreModel;
import org.example.model.Player;

public class MatchScoreCalculationService {

    public void incrementScore(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        if (matchScoreModel.isTieBreak()) {
            incrementTieBreakScore(matchScoreModel, scoringPlayer);
        } else {
            incrementRegularScore(matchScoreModel, scoringPlayer);
        }
    }

    private void incrementRegularScore(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        Match match = matchScoreModel.getMatch();
        boolean isPlayer1 = scoringPlayer.equals(match.getPlayer1());

        int currentPoints = isPlayer1 ? matchScoreModel.getPointsPlayer1() : matchScoreModel.getPointsPlayer2();
        int opponentPoints = isPlayer1 ? matchScoreModel.getPointsPlayer2() : matchScoreModel.getPointsPlayer1();

        if (currentPoints == 0) {
            currentPoints = 15;
        } else if (currentPoints == 15) {
            currentPoints = 30;
        } else if (currentPoints == 30) {
            currentPoints = 40;
        } else if (currentPoints == 40) {
            if (opponentPoints == 40) {
                currentPoints = 50; // Advantage
            } else if (currentPoints == 50) {
                awardGame(matchScoreModel, scoringPlayer);
                return;
            } else {
                awardGame(matchScoreModel, scoringPlayer);
                return;
            }
        }

        if (isPlayer1) {
            matchScoreModel.setPointsPlayer1(currentPoints);
        } else {
            matchScoreModel.setPointsPlayer2(currentPoints);
        }
    }

    private void incrementTieBreakScore(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setTieBreakPointsPlayer1(matchScoreModel.getTieBreakPointsPlayer1() + 1);
        } else {
            matchScoreModel.setTieBreakPointsPlayer2(matchScoreModel.getTieBreakPointsPlayer2() + 1);
        }

        checkTieBreakWin(matchScoreModel);
    }

    private void checkTieBreakWin(MatchScoreModel matchScoreModel) {
        int p1TieBreakPoints = matchScoreModel.getTieBreakPointsPlayer1();
        int p2TieBreakPoints = matchScoreModel.getTieBreakPointsPlayer2();

        if ((p1TieBreakPoints >= 7 && p1TieBreakPoints - p2TieBreakPoints >= 2) ||
                (p2TieBreakPoints >= 7 && p2TieBreakPoints - p1TieBreakPoints >= 2)) {

            Player winner = (p1TieBreakPoints > p2TieBreakPoints) ?
                    matchScoreModel.getMatch().getPlayer1() :
                    matchScoreModel.getMatch().getPlayer2();

            awardSet(matchScoreModel, winner);
            matchScoreModel.setTieBreak(false);
            matchScoreModel.setTieBreakPointsPlayer1(0);
            matchScoreModel.setTieBreakPointsPlayer2(0);
        }
    }

    private void awardGame(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setGamesPlayer1(matchScoreModel.getGamesPlayer1() + 1);
        } else {
            matchScoreModel.setGamesPlayer2(matchScoreModel.getGamesPlayer2() + 1);
        }

        matchScoreModel.setPointsPlayer1(0);
        matchScoreModel.setPointsPlayer2(0);

        checkSetWin(matchScoreModel);
    }

    private void checkSetWin(MatchScoreModel matchScoreModel) {
        int p1Games = matchScoreModel.getGamesPlayer1();
        int p2Games = matchScoreModel.getGamesPlayer2();

        if (p1Games == 6 && p2Games == 6) {
            matchScoreModel.setTieBreak(true);
            return;
        }

        if ((p1Games >= 6 && p1Games - p2Games >= 2) || (p2Games >= 6 && p2Games - p1Games >= 2)) {
            Player winner = (p1Games > p2Games) ?
                    matchScoreModel.getMatch().getPlayer1() :
                    matchScoreModel.getMatch().getPlayer2();

            awardSet(matchScoreModel, winner);
        }
    }

    private void awardSet(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setSetsPlayer1(matchScoreModel.getSetsPlayer1() + 1);
        } else {
            matchScoreModel.setSetsPlayer2(matchScoreModel.getSetsPlayer2() + 1);
        }

        matchScoreModel.setGamesPlayer1(0);
        matchScoreModel.setGamesPlayer2(0);

        checkMatchWin(matchScoreModel);
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
