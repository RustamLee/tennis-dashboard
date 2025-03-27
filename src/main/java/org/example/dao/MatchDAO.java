package org.example.dao;

import org.example.model.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class MatchDAO implements GenericDAO<Match> {
    private EntityManager entityManager;

    public MatchDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("tennis-dashboard").createEntityManager();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Match findById(Long id) {
        return entityManager.find(Match.class, id);
    }

    @Override
    public List<Match> findAll() {
        return entityManager.createQuery("from Match", Match.class).getResultList();
    }
}
