package edu.sjsu.cs.cs151.model;

import java.util.ArrayList;

/**
 * A class to store the rank of the card
 */
public class RankedHandChecker {

    private int rankedHandScore;
    private ArrayList<Card> cardToBeAnalyzed = new ArrayList<>();
    private RankedHandType rankedHandType;
    private


    public RankedHandChecker(ArrayList<Card> card) {
        cardToBeAnalyzed.addAll(card);
        setRankedHandType();
    }

    private void setRankedHandType() {
        if (isHighCard())           rankedHandType = RankedHandType.HIGH_CARD;
        else if (isOnePair())       rankedHandType = RankedHandType.ONE_PAIR;
        else if (isTwoPair())       rankedHandType = RankedHandType.TWO_PAIRS;
        else if (isSet())           rankedHandType = RankedHandType.THREE_OF_A_KIND;
        else if (isStraight())      rankedHandType = RankedHandType.STRAIGHT;
        else if (isFlush())         rankedHandType = RankedHandType.FLUSH;
        else if (isFullHouse())         rankedHandType = RankedHandType.FULL_HOUSE;
        else if (isFourOfAKind())   rankedHandType = RankedHandType.FOUR_OF_A_KIND;
        else if (isStraightFlush()) rankedHandType = RankedHandType.STRAIGHT_FLUSH;
        else                        rankedHandType = RankedHandType.ROYAL_FLUSH;
    }

    // Check for High Card
    private boolean isHighCard() {

        return false;
    }

    // Check for 1 Pair
    private boolean isOnePair() {
        return false;
    }

    // Check for 2 Pairs
    private boolean isTwoPair() {
        return false;
    }

    // Check for a Set
    private boolean isSet() {
        return false;
    }

    // Check for a straight
    private boolean isStraight() {
        return false;
    }

    // check for a flush
    private boolean isFlush() {
        return false;
    }

    // check for a full house
    private boolean isFullHouse() {
        return false;
    }

    // check for 4 of a Kind
    private boolean isFourOfAKind() {
        return false;
    }

    // Check for straight flush
    private boolean isStraightFlush() {
        return false;
    }

    // Check for Royal Flush
    private boolean isRoyalFlush() {
        return false;
    }

    /**
     * Set/Get Method
     */

    public RankedHandType getRankedHandType() {
        return rankedHandType;
    }

    public int getRankedHandScore() {
        return rankedHandScore;
    }

    public ArrayList<Card> getCardToBeAnalyzed() {
        return cardToBeAnalyzed;
    }

    public void setCardToBeAnalyzed(ArrayList<Card> cardToBeAnalyzed) {
        this.cardToBeAnalyzed = cardToBeAnalyzed;
    }
}
