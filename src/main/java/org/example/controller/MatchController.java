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
import org.example.model.MatchScoreModel;
import org.example.model.Player;
import org.example.service.FinishedMatchesPersistenceService;
import org.example.service.MatchScoreCalculationService;
import org.example.service.OngoingMatchesService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "MatchController", urlPatterns = "/new-match")
public class MatchController extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private PlayerDAO playerDAO;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("ongoingMatchesService", new OngoingMatchesService());
        getServletContext().setAttribute("finishedMatchesPersistenceService", new FinishedMatchesPersistenceService());
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tennis-dashboard");
        playerDAO = new PlayerDAO(emf);
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

    public MatchController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerOneName = request.getParameter("playerOne");
        String playerTwoName = request.getParameter("playerTwo");

        if (playerOneName == null || playerTwoName == null || playerOneName.trim().isEmpty() || playerTwoName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Both player names must be provided.");
            return;
        }

        Player playerOne = playerDAO.findByName(playerOneName);
        if (playerOne == null) {
            playerOne = new Player(playerOneName);
            playerDAO.save(playerOne);
        }

        Player playerTwo = playerDAO.findByName(playerTwoName);
        if (playerTwo == null) {
            playerTwo = new Player(playerTwoName);
            playerDAO.save(playerTwo);
        }

        Match match = new Match(playerOne, playerTwo);
        MatchScoreModel matchScoreModel = new MatchScoreModel(match);
        UUID matchUuid = match.getMatchUuid();
        ongoingMatchesService.addMatch(matchScoreModel);
        System.out.println("Added match with UUID: " + match.getMatchUuid());

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + matchUuid);
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
