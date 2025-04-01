package org.example.service;

import org.example.model.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private Map<UUID, Match> ongoingMatches = new HashMap<>();

    public void addMatch(Match match) {
        ongoingMatches.put(match.getMatchUuid(), match);
    }

    public Match getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public void removeMatch(UUID uuid) {ongoingMatches.remove(uuid);
    }

    public Map<UUID, Match> getAllMatches() {
        return ongoingMatches;
    }
}
