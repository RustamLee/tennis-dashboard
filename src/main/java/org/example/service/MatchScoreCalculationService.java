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

        // Если у игрока уже Advantage, он выигрывает гейм
        if (currentPoints == 50) {
            awardGame(matchScoreModel, scoringPlayer);
            return;
        }

        // Если оба игрока на 40, начисляем Advantage
        if (currentPoints == 40 && opponentPoints == 40) {
            if (isPlayer1) {
                matchScoreModel.setPointsPlayer1(50);
            } else {
                matchScoreModel.setPointsPlayer2(50);
            }
            return;
        }

        // Если у игрока 40 и соперник не на 40 — победа в гейме
        if (currentPoints == 40) {
            awardGame(matchScoreModel, scoringPlayer);
            return;
        }

        // Увеличение очков по стандартной схеме (0 → 15 → 30 → 40)
        int nextPoints = switch (currentPoints) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> currentPoints;
        };

        if (isPlayer1) {
            matchScoreModel.setPointsPlayer1(nextPoints);
        } else {
            matchScoreModel.setPointsPlayer2(nextPoints);
        }
    }


    public void incrementTieBreakScore(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setTieBreakPointsPlayer1(matchScoreModel.getTieBreakPointsPlayer1() + 1);
        } else {
            matchScoreModel.setTieBreakPointsPlayer2(matchScoreModel.getTieBreakPointsPlayer2() + 1);
        }

        checkTieBreakWin(matchScoreModel);
    }

    public void checkTieBreakWin(MatchScoreModel matchScoreModel) {
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

    public void awardGame(MatchScoreModel matchScoreModel, Player scoringPlayer) {
        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setGamesPlayer1(matchScoreModel.getGamesPlayer1() + 1);
        } else {
            matchScoreModel.setGamesPlayer2(matchScoreModel.getGamesPlayer2() + 1);
        }
        matchScoreModel.setPointsPlayer1(0);
        matchScoreModel.setPointsPlayer2(0);
        System.out.println(matchScoreModel.getMatch().getPlayer1());
        checkSetWin(matchScoreModel);
    }

    public void checkSetWin(MatchScoreModel matchScoreModel) {
        int p1Games = matchScoreModel.getGamesPlayer1();
        int p2Games = matchScoreModel.getGamesPlayer2();
        System.out.println("In checkSetWin: P1 games = " + p1Games + ", P2 games = " + p2Games);

        if (p1Games == 6 && p2Games == 6) {
            matchScoreModel.setTieBreak(true);
            return;
        }
        if ((p1Games >= 6 && p1Games - p2Games >= 2) || (p2Games >= 6 && p2Games - p1Games >= 2)) {
            Player winner = (p1Games > p2Games) ?
                    matchScoreModel.getMatch().getPlayer1() :
                    matchScoreModel.getMatch().getPlayer2();
            System.out.println("Checking set win: P1 games = " + p1Games + ", P2 games = " + p2Games);
            awardSet(matchScoreModel, winner);
        }
    }

    public void awardSet(MatchScoreModel matchScoreModel, Player scoringPlayer) {

        boolean isPlayer1 = scoringPlayer.equals(matchScoreModel.getMatch().getPlayer1());

        if (isPlayer1) {
            matchScoreModel.setSetsPlayer1(matchScoreModel.getSetsPlayer1() + 1);
        } else {
            matchScoreModel.setSetsPlayer2(matchScoreModel.getSetsPlayer2() + 1);
        }

        matchScoreModel.setGamesPlayer1(0);
        matchScoreModel.setGamesPlayer2(0);
        System.out.println("Awarding set to: " + scoringPlayer.getName());
        checkMatchWin(matchScoreModel);
    }

    public void checkMatchWin(MatchScoreModel matchScoreModel) {
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
