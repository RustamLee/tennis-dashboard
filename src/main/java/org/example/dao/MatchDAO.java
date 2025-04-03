package org.example.dao;

import jakarta.persistence.TypedQuery;
import org.example.model.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class MatchDAO {
    private EntityManager entityManager;

    public MatchDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("tennis-dashboard").createEntityManager();
    }

    public void save(Match match) {
        if (match == null || match.getPlayer1() == null || match.getPlayer2() == null) {
            throw new IllegalArgumentException("Match and players cannot be null");
        }
        if (match.getPlayer1().getName().length() > 15 || match.getPlayer2().getName().length() > 15) {
            throw new IllegalArgumentException("Player name cannot be longer than 15 characters");
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(match);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }


    public Match findById(Long id) {
        return entityManager.find(Match.class, id);
    }


    public List<Match> getMatches(int page, String playerName) {
        String query = "SELECT m FROM Match m " +
                "JOIN m.player1 p1 " +
                "JOIN m.player2 p2 ";

        if (playerName != null && !playerName.trim().isEmpty()) {
            query += "WHERE LOWER(p1.name) LIKE :name OR LOWER(p2.name) LIKE :name ";
        }

        query += "ORDER BY m.id DESC";

        TypedQuery<Match> typedQuery = entityManager.createQuery(query, Match.class);

        if (playerName != null && !playerName.trim().isEmpty()) {
            typedQuery.setParameter("name", "%" + playerName.toLowerCase() + "%");
        }

        return typedQuery.setFirstResult((page - 1) * 10)
                .setMaxResults(10)
                .getResultList();
    }



    public int getTotalMatches(String playerName) {
        String query = "SELECT COUNT(m) FROM Match m " +
                "JOIN m.player1 p1 " +
                "JOIN m.player2 p2 ";

        if (playerName != null && !playerName.trim().isEmpty()) {
            query += "WHERE LOWER(p1.name) LIKE :name OR LOWER(p2.name) LIKE :name";
        }

        TypedQuery<Long> typedQuery = entityManager.createQuery(query, Long.class);

        if (playerName != null && !playerName.trim().isEmpty()) {
            typedQuery.setParameter("name", "%" + playerName.toLowerCase() + "%");
        }

        Long count = typedQuery.getSingleResult();
        return count.intValue();
    }


}
