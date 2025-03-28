package org.example.service;

import org.example.model.Match;

import java.util.HashMap;
import java.util.Map;

public class OngoingMatchesService {

    private Map<Long, Match> ongoingMatches = new HashMap<>();

    public void addMatch(Match match) {
        ongoingMatches.put(match.getId(), match);
    }

    public Match getMatch(Long id) {
        return ongoingMatches.get(id);
    }

    public void removeMatch(Long id) {
        ongoingMatches.remove(id);
    }

    public Map<Long, Match> getAllMatches() {
        return ongoingMatches;
    }
}
