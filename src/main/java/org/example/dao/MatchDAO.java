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
                "JOIN m.player2 p2 " +
                "WHERE (:name IS NULL OR LOWER(p1.name) LIKE LOWER(:name) OR LOWER(p2.name) LIKE LOWER(:name)) " +
                "ORDER BY m.id DESC";

        return entityManager.createQuery(query, Match.class)
                .setParameter("name", playerName != null ? "%" + playerName.toLowerCase() + "%" : null)
                .setFirstResult((page - 1) * 10)
                .setMaxResults(10)
                .getResultList();
    }


    public int getTotalMatches(String playerName) {
        String query = "SELECT COUNT(m) FROM Match m " +
                "JOIN m.player1 p1 " +
                "JOIN m.player2 p2 " +
                "WHERE (:name IS NULL OR p1.name LIKE :name OR p2.name LIKE :name)";

        Long count = (Long) entityManager.createQuery(query)
                .setParameter("name", playerName != null ? "%" + playerName + "%" : null)
                .getSingleResult();
        return count.intValue();
    }

}
