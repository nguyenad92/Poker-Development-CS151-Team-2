package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

/**
 * A class to keep cards of players
 *
 */
public class PlayerHand {
    private Player player;
    private ArrayList<Card> cards;

    /**
     * Construct a playerHand object
     */
    public PlayerHand(Player p, ArrayList<Card> cards) {
        player = p;
        this.cards = cards;
    }

    public ArrayList<Card> getCard() {
        return cards;
    }

    public Player getPlayer() {
        return player;
    }
}
