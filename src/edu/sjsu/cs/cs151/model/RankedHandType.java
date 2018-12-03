package edu.sjsu.cs.cs151.model;

/**
 * Type of Hand and the score for each Hand.
 */
public enum RankedHandType {
    ROYAL_FLUSH("Royal Flush", 9),
    STRAIGHT_FLUSH("Straight Flush", 8),
    FOUR_OF_A_KIND("Four of Kind", 7),
    FULL_HOUSE("Full House", 6),
    FLUSH("Flush", 5),
    STRAIGHT("Straight", 4),
    THREE_OF_A_KIND("Three of Kind", 3),
    TWO_PAIRS("Two Pairs", 2),
    ONE_PAIR("One Pair", 1),
    HIGH_CARD("High Card", 0);
    
    private int score;
    private String handType;

    /**
     * Constructor: Initialize the Type of Hand
     * @param handType: TypeName of the Hand
     * @param score: Score corresponding to the Hand
     */
    RankedHandType(String handType, int score) {
        this.score = score;
        this.handType = handType;
    }

    /**
     * Get Score of this type of Hand
     * @return Integer
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the type name of this hand
     * @return String
     */
    public String getHandType() {
        return handType;
    }
}
