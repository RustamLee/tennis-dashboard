package org.example.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Match;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try{
            Long matchId = Long.parseLong(id);
            Match match = ongoingMatchesService.getMatch(matchId);
            if (match == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }
            request.setAttribute("match", match);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/match-score.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid match ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try {
            Long matchId = Long.parseLong(id);
            Match match = ongoingMatchesService.getMatch(matchId);

            if (match == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }

            String winner = request.getParameter("winner");
            if ("player1".equals(winner)) {
                match.incrementScore(match.getPlayer1());
            } else if ("player2".equals(winner)) {
                match.incrementScore(match.getPlayer2());
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid winner parameter");
                return;
            }

            if (match.isFinished()) {
                ongoingMatchesService.removeMatch(match.getId());
                finishedMatchesPersistenceService.saveMatch(match);
                request.setAttribute("match", match);
                request.getRequestDispatcher("/WEB-INF/views/final-score.jsp").forward(request, response);
            } else {
                request.setAttribute("match", match);
                request.getRequestDispatcher("/WEB-INF/views/match-score.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid match ID format");
        }
    }


}
