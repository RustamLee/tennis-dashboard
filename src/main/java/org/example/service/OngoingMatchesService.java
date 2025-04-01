package org.example.service;

import org.example.model.Match;
import org.example.model.MatchScoreModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private final Map<UUID, MatchScoreModel> ongoingMatches = new HashMap<>();

    public void addMatch(MatchScoreModel matchScoreModel) {
        ongoingMatches.put(matchScoreModel.getMatch().getMatchUuid(), matchScoreModel);
    }

    public MatchScoreModel getMatchScoreModel(UUID matchUuid) {
        return ongoingMatches.get(matchUuid);
    }

    public void removeMatch(UUID matchUuid) {
        ongoingMatches.remove(matchUuid);
    }
}
