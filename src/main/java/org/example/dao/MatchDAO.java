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

    public void update(Match match) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(match);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void delete(Match match) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(match) ? match : entityManager.merge(match));
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public Match findById(Long id) {
        return entityManager.find(Match.class, id);
    }

    public List<Match> findAll() {
        return entityManager.createQuery("from Match", Match.class).getResultList();
    }

    public List<Match> getMatches(int page, String playerName) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        String query = "SELECT m FROM Match m WHERE m.player1.name LIKE :playerName OR m.player2.name LIKE :playerName ORDER BY m.id DESC";

        TypedQuery<Match> typedQuery = entityManager.createQuery(query, Match.class);
        typedQuery.setParameter("playerName", "%" + playerName + "%");
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

}
