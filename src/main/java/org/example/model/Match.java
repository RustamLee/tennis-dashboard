package org.example.model;

import jakarta.persistence.*;


@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = true)
    private Player winner;

    @Column(name = "score_player1")
    private int scorePlayer1 = 0;

    @Column(name = "score_player2")
    private int scorePlayer2 = 0;

    public Match() {
    }

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public void incrementScore(Player player) {
        if (player.equals(player1)) {
            scorePlayer1++;
        } else if (player.equals(player2)) {
            scorePlayer2++;
        }
    }

    public boolean isFinished() {
        if (scorePlayer1 >= 11 || scorePlayer2 >= 11) {
            winner = (scorePlayer1 > scorePlayer2) ? player1 : player2;
            return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public Player getPlayerByName(String name) {
        if (player1 != null && player1.getName().equals(name)) {
            return player1;
        } else if (player2 != null && player2.getName().equals(name)) {
            return player2;
        }
        return null;
    }
}
