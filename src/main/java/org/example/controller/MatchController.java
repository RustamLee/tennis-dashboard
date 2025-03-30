package org.example.controller;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.PlayerDAO;
import org.example.model.Match;
import org.example.model.Player;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.MatchScoreCalculationService;
import org.example.service.OngoingMatchesService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MatchController", urlPatterns = "/new-match")
public class MatchController extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("tennis-dashboard");

    private PlayerDAO playerDAO = new PlayerDAO(emf);
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    public MatchController() {
        super();
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

            String winnerName = request.getParameter("winner");
            Player winner = match.getPlayerByName(winnerName);

            if (winner == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid winner name");
                return;
            }
            matchScoreCalculationService.updateScore(match, winner);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("MatchController is running!");
        System.out.println("MatchController: doGet() called");
        request.getRequestDispatcher("/WEB-INF/views/new-match.jsp").forward(request, response);
    }

}
