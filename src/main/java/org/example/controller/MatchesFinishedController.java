package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.MatchDAO;
import org.example.model.Match;

import java.io.IOException;
import java.util.List;

public class MatchesFinishedController extends HttpServlet {
    private MatchDAO matchDAO = new MatchDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");

        List<Match> matches = matchDAO.getMatches(page, playerName);

        request.setAttribute("matches", matches);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/matches.jsp");
        dispatcher.forward(request, response);
    }
}
