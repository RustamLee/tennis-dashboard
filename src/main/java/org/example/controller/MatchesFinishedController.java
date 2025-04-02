package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.MatchDAO;
import org.example.model.Match;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesFinishedController extends HttpServlet {
        private MatchDAO matchDAO = new MatchDAO();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String pageParam = request.getParameter("page");
            String playerName = request.getParameter("filter_by_player_name");

            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

            List<Match> matches = matchDAO.getMatches(page, playerName);
            System.out.println("Matches found: " + matches.size());

            int totalMatches = matchDAO.getTotalMatches(playerName);
            int totalPages = (int) Math.ceil((double) totalMatches / 10);

            request.setAttribute("matches", matches);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("filter_by_player_name", playerName);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/matches.jsp");
            dispatcher.forward(request, response);
        }
    }

