package org.example.dao;

import org.example.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class PlayerDAO implements GenericDAO<Player> {
    private EntityManager entityManager;

    public PlayerDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("tennis-dashboard").createEntityManager();
    }

    @Override
    public void save(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(player);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(player);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(player) ? player : entityManager.merge(player));
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Player findById(Long id) {
        return entityManager.find(Player.class, id);
    }

    @Override
    public List<Player> findAll() {
        return entityManager.createQuery("from Player", Player.class).getResultList();
    }
}
