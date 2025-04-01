package org.example.model;

import java.util.UUID;

public class MatchScoreModel {
    private final Match match;
    private int setsPlayer1;
    private int setsPlayer2;
    private int gamesPlayer1;
    private int gamesPlayer2;
    private boolean isTieBreak;
    private int tieBreakPointsPlayer1;
    private int tieBreakPointsPlayer2;
    private int pointsPlayer1;
    private int pointsPlayer2;

    public MatchScoreModel(Match match) {
        this.match = match;
        this.isTieBreak = false;
        // defoult values
        this.setsPlayer1 = 0;
        this.setsPlayer2 = 0;
        this.gamesPlayer1 = 0;
        this.gamesPlayer2 = 0;
        this.pointsPlayer1 = 0;
        this.pointsPlayer2 = 0;
    }

    public Match getMatch() {
        return match;
    }

    public UUID getUiidMatch() {
        return this.match.getMatchUuid();
    }

    public int getSetsPlayer1() {
        return setsPlayer1;
    }

    public int getSetsPlayer2() {
        return setsPlayer2;
    }

    public int getGamesPlayer1() {
        return gamesPlayer1;
    }

    public int getGamesPlayer2() {
        return gamesPlayer2;
    }

    public int getPointsPlayer1() {
        return pointsPlayer1;
    }

    public int getPointsPlayer2() {
        return pointsPlayer2;
    }

    // methods for MatchScoreCalculationService
    public void setSetsPlayer1(int setsPlayer1) {
        this.setsPlayer1 = setsPlayer1;
    }

    public void setSetsPlayer2(int setsPlayer2) {
        this.setsPlayer2 = setsPlayer2;
    }

    public void setGamesPlayer1(int gamesPlayer1) {
        this.gamesPlayer1 = gamesPlayer1;
    }

    public void setGamesPlayer2(int gamesPlayer2) {
        this.gamesPlayer2 = gamesPlayer2;
    }

    public void setPointsPlayer1(int pointsPlayer1) {
        this.pointsPlayer1 = pointsPlayer1;
    }

    public void setPointsPlayer2(int pointsPlayer2) {
        this.pointsPlayer2 = pointsPlayer2;
    }
    public boolean isTieBreak() {
        return isTieBreak;
    }

    public void setTieBreak(boolean tieBreak) {
        this.isTieBreak = tieBreak;
    }

    public int getTieBreakPointsPlayer1() {
        return tieBreakPointsPlayer1;
    }

    public void setTieBreakPointsPlayer1(int points) {
        this.tieBreakPointsPlayer1 = points;
    }

    public int getTieBreakPointsPlayer2() {
        return tieBreakPointsPlayer2;
    }

    public void setTieBreakPointsPlayer2(int points) {
        this.tieBreakPointsPlayer2 = points;
    }
}
