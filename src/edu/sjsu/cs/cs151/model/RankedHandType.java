package edu.sjsu.cs.cs151.model;

/**
 * Type of Hand and the score for each Hand.
 */
public enum RankedHandType {

    /** Royal flush */
    ROYAL_FLUSH("Royal Flush", 9),

    /** Straight Flush */
    STRAIGHT_FLUSH("Straight Flush", 8),

    /** Four of Kind */
    FOUR_OF_A_KIND("Four of Kind", 7),

    /** Full House */
    FULL_HOUSE("Full House", 6),

    /** Flush */
    FLUSH("Flush", 5),

    /** Straight */
    STRAIGHT("Straight", 4),

    /** Three of Kind */
    THREE_OF_A_KIND("Three of Kind", 3),

    /** Two Pairs */
    TWO_PAIRS("Two Pairs", 2),

    /** One Pair */
    ONE_PAIR("One Pair", 1),

    /** Highest Card */
    HIGH_CARD("High Card", 0);
    
    private int score;
    private String handType;
    
    RankedHandType(String handType, int score) {
        this.score = score;
        this.handType = handType;
    }

    public int getScore() {
        return score;
    }

    public String getHandType() {
        return handType;
    }
}
