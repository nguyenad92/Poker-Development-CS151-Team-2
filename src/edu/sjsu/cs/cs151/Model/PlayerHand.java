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

    /**
     * get 2 cards from the deck of card
     * @return cards from the deck of card
     */
    public ArrayList<Card> getCard() {
        return cards;
    }

    /**
     * get players
     * @return
     */
    public Player getPlayer() {
        return player;
    }
}
