package org.example.service;

import org.example.dao.MatchDAO;
import org.example.model.Match;

public class FinishedMatchesPersistenceService {

    private final MatchDAO matchDAO;

    public FinishedMatchesPersistenceService() {
        this.matchDAO = new MatchDAO();
    }

    public void saveMatch(Match match) {
        matchDAO.save(match);
    }
}