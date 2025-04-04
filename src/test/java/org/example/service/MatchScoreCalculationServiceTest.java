package org.example.service;

import org.example.model.Match;
import org.example.model.MatchScoreModel;
import org.example.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService service;
    private Match match;
    private MatchScoreModel matchScoreModel;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        service = new MatchScoreCalculationService();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        match = new Match(player1, player2);
        matchScoreModel = new MatchScoreModel(match);
    }
    // standard score increase 0 -> 15 -> 30 -> 40 -> win
    @Test
    void testIncrementScore_RegularGameProgression() {
        service.incrementScore(matchScoreModel, player1);
        assertEquals(15, matchScoreModel.getPointsPlayer1());

        service.incrementScore(matchScoreModel, player1);
        assertEquals(30, matchScoreModel.getPointsPlayer1());

        service.incrementScore(matchScoreModel, player1);
        assertEquals(40, matchScoreModel.getPointsPlayer1());
    }

    // both players have 40 points and one gets an advantage
    @Test
    void testIncrementScore_DeuceToAdvantage() {
        matchScoreModel.setPointsPlayer1(40);
        matchScoreModel.setPointsPlayer2(40);

        service.incrementScore(matchScoreModel, player1);

        assertTrue(matchScoreModel.isPlayer1HasAdvantage());
        assertFalse(matchScoreModel.isPlayer2HasAdvantage());
        assertEquals(40, matchScoreModel.getPointsPlayer1());
    }


    //  player wins the game after gaining an advantage
    @Test
    void testIncrementScore_AdvantageToGameWin() {
        matchScoreModel.setPointsPlayer1(40);
        matchScoreModel.setPointsPlayer2(40);
        matchScoreModel.setPlayer1HasAdvantage(true);

        service.incrementScore(matchScoreModel, player1);

        assertEquals(1, matchScoreModel.getGamesPlayer1());
        assertEquals(0, matchScoreModel.getPointsPlayer1());
        assertEquals(0, matchScoreModel.getPointsPlayer2());
        assertFalse(matchScoreModel.isPlayer1HasAdvantage());
        assertFalse(matchScoreModel.isPlayer2HasAdvantage());
    }


    // game doesn't finish if score is 40/40
    @Test
    void testIncrementScore_DeuceNoGameWin() {
        matchScoreModel.setPointsPlayer1(40);
        matchScoreModel.setPointsPlayer2(40);

        service.incrementScore(matchScoreModel, player1);

        assertTrue(matchScoreModel.isPlayer1HasAdvantage());
        assertFalse(matchScoreModel.isPlayer2HasAdvantage());
        assertEquals(40, matchScoreModel.getPointsPlayer1());
        assertEquals(40, matchScoreModel.getPointsPlayer2());
        assertEquals(0, matchScoreModel.getGamesPlayer1());
        assertEquals(0, matchScoreModel.getGamesPlayer2());
    }


    // player wins the game when the score is 40-0
    @Test
    void testIncrementScore_GameWinAt40_0() {
        matchScoreModel.setPointsPlayer1(40);
        matchScoreModel.setPointsPlayer2(0);

        service.incrementScore(matchScoreModel, player1);
        assertEquals(1, matchScoreModel.getGamesPlayer1());
        assertEquals(0, matchScoreModel.getPointsPlayer1());
        assertEquals(0, matchScoreModel.getPointsPlayer2());
    }


    // if score is 6/6, a tiebreak begins instead of a regular game
    @Test
    void testTieBreakStartsAt6_6() {
        matchScoreModel.setGamesPlayer1(6);
        matchScoreModel.setGamesPlayer2(6);

        service.checkSetWin(matchScoreModel);

        assertTrue(matchScoreModel.isTieBreak(), "Expected tie-break to start at 6-6");
    }



    // Checking for a tiebreak win at 7-5
    @Test
    void testTieBreakWin() {
        matchScoreModel.setTieBreak(true);
        matchScoreModel.setTieBreakPointsPlayer1(6);
        matchScoreModel.setTieBreakPointsPlayer2(5);

        service.incrementScore(matchScoreModel, player1);
        assertEquals(1, matchScoreModel.getSetsPlayer1());
        assertFalse(matchScoreModel.isTieBreak());
    }

    // after winning in two sets, the match is over
    @Test
    void matchShouldBeMarkedAsFinishedWhenAPlayerWinsTwoSets() {
        service.awardSet(matchScoreModel, player1);
        service.awardSet(matchScoreModel, player1);

        assertEquals(player1, match.getWinner(), "After winning two sets, the match must be completed and a winner must be determined.");
    }

}
