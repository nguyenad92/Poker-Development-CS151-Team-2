package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

/**
 * A class to use to analyse the cards and rank the players based on it
 *
 */

public class RankedHand implements Comparable<RankedHand> {

    private int rankedHandScore;
    private RankedHandType rankedHandType;

    public RankedHand(Player p) {
        System.out.println(p.getPlayerHands().size());
        initRankedHand(p.getPlayerHands());
    }

    private void initRankedHand(ArrayList<Card> cards) {
        RankedHandChecker rankedHandChecker = new RankedHandChecker(cards);
        rankedHandScore = rankedHandChecker.getRankedHandScore();
        rankedHandType = rankedHandChecker.getRankedHandType();
    }

    public void setRankedHandScore(int rankedHandScore) {
        this.rankedHandScore = rankedHandScore;
    }

    public void setRankedHandType(RankedHandType rankedHandType) {
        this.rankedHandType = rankedHandType;
    }

    public RankedHandType getRankedHandType() {
        return rankedHandType;
    }

    public int getRankedHandScore() {
        return rankedHandScore;
    }

    @Override
    public int compareTo(RankedHand o) {
        return 0;
    }
}
