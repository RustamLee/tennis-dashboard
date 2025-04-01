package org.example.model;

import jakarta.persistence.*;
import java.util.UUID;

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

    @Transient // use only for not finalized matches
    private UUID matchUuid;

    public Match() {}

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchUuid = UUID.randomUUID();
    }

    public UUID getMatchUuid() {
        return matchUuid;
    }
    public void setMatchUuid(UUID matchUuid) {
        this.matchUuid = matchUuid;
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

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", winner=" + winner +
                ", matchUuid=" + matchUuid +
                '}';
    }
}
