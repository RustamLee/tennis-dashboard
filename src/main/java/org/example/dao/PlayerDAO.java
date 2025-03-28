package org.example.dao;

import jakarta.persistence.*;
import org.example.model.Player;


import java.util.List;

public class PlayerDAO {
    private EntityManager entityManager;

    public PlayerDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("tennis-dashboard").createEntityManager();
    }

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

    public Player findByName(String name) {
        try {
            return entityManager.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {

            return null;
        } catch (PersistenceException e) {
            throw new RuntimeException("Error getting player by name: " + name, e);
        } catch (RuntimeException e) {
            throw e;
        }
    }



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

    public Player findById(Long id) {
        return entityManager.find(Player.class, id);
    }


    public List<Player> findAll() {
        return entityManager.createQuery("from Player", Player.class).getResultList();
    }
}
