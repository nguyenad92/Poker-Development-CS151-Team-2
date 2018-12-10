package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

/**
 * A class to use to analyse the cards and rank the players based on it
 *
 */

public class RankedHand implements Comparable<RankedHand> {

    private int rankedHandScore;
    private RankedHandType rankedHandType;

    /**
     * player's rank hand
     * @param p
     */
    public RankedHand(Player p) {
        initRankedHand(p.getPlayerHands());
    }

    /**
     * get the rank hand score and type of the players
     * @param cards
     */
    private void initRankedHand(ArrayList<Card> cards) {
        RankedHandChecker rankedHandChecker = new RankedHandChecker(cards);
        rankedHandScore = rankedHandChecker.getRankedHandScore();
        rankedHandType = rankedHandChecker.getRankedHandType();
    }

    /**
     * get/set method
     */

    public RankedHandType getRankedHandType() {
        return rankedHandType;
    }

    public int getRankedHandScore() {
        return rankedHandScore;
    }

    /**
     * compare rank hand
     * @param o
     * @return
     */
    @Override
    public int compareTo(RankedHand o) {
        return 0;
    }
}
