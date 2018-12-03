package edu.sjsu.cs.cs151.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * A class to keep cards of players
 *
 */
public class PlayerHand {
//    private edu.sjsu.cs.cs151.model.Player player;
    ArrayList<Card> cards;

    /**
     * Construct a playerHand object
     */
    public PlayerHand(ArrayList<Card> cards) {
//        this.player = player;
        this.cards = cards;
    }

//    public edu.sjsu.cs.cs151.model.Player getPlayer() {
//        return player;
//    }

    public ArrayList<Card> getCard() {
        return cards;
    }

    public void playerHand() {

    }

    public int compareRankHand() {
        return 0;
    }

}
