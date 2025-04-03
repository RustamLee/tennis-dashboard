package org.example.dao;

import jakarta.persistence.*;
import org.example.model.Player;


import java.util.List;

public class PlayerDAO {

    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PlayerDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public void save(Player player) {
        if (player.getName() == null || player.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (player.getName().length() > 15) {
            throw new IllegalArgumentException("Player name cannot be longer than 15 characters");
        }
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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }

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
}
