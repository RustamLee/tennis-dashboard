package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Match;
import org.example.model.MatchScoreModel;
import org.example.model.Player;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.MatchScoreCalculationService;
import org.example.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private MatchScoreCalculationService matchScoreCalculationService;

    @Override
    public void init() throws ServletException {
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidParam = request.getParameter("uuid");
        if (uuidParam == null || uuidParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Match ID is missing");
            return;
        }
        try {
            UUID matchUuid = UUID.fromString(uuidParam);
            Match match = ongoingMatchesService.getMatch(matchUuid);
            if (match == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }
            MatchScoreModel matchScoreModel = new MatchScoreModel(match);
            request.setAttribute("matchScore", matchScoreModel);
            request.getRequestDispatcher("/WEB-INF/views/match-score.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid match ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        try {
            UUID matchUuid = UUID.fromString(uuid);
            Match match = ongoingMatchesService.getMatch(matchUuid);
            if (match == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }

            MatchScoreModel matchScoreModel = new MatchScoreModel(match);
            String winner = request.getParameter("winner");
            if (winner == null || winner.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Winner parameter is missing");
                return;
            }

            Player scoringPlayer = "player1".equals(winner) ? match.getPlayer1() : match.getPlayer2();
            matchScoreCalculationService.incrementScore(matchScoreModel, scoringPlayer);

            if (match.getWinner() != null) {
                ongoingMatchesService.removeMatch(match.getMatchUuid());
                finishedMatchesPersistenceService.saveMatch(match);
                request.setAttribute("match", match);
                request.getRequestDispatcher("/WEB-INF/views/final-score.jsp").forward(request, response);
            } else {
                request.setAttribute("matchScore", matchScoreModel);
                request.getRequestDispatcher("/WEB-INF/views/match-score.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid match ID format");
        }
    }
}