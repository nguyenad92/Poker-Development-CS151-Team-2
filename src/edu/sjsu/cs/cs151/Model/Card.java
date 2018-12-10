package edu.sjsu.cs.cs151.Model;

/**
 * A generic game card in a deck 
 * 
 * Its value is determined first by rank, then by suit.
 * 
 * @author Oscar Stigter
 */
public class Card implements Comparable<Card> {

    /** The number of ranks in a deck. */
    public static final int NO_OF_RANKS = 13;

    /** The number of suits in a deck. */
    public static final int NO_OF_SUITS = 4;
    
    // The ranks with its integer values.
    public static final int ACE      = 12;
    public static final int KING     = 11;
    public static final int QUEEN    = 10;
    public static final int JACK     = 9;
    public static final int TEN      = 8;
    public static final int NINE     = 7;
    public static final int EIGHT    = 6;
    public static final int SEVEN    = 5;
    public static final int SIX      = 4;
    public static final int FIVE     = 3;
    public static final int FOUR     = 2;
    public static final int THREE    = 1;
    public static final int DEUCE    = 0;
    
    // The suits with integer values.
    public static final int SPADES   = 3;
    public static final int HEARTS   = 2;
    public static final int CLUBS    = 1;
    public static final int DIAMONDS = 0;
    
    //The rank according to string
    public static final String[] RANK_SYMBOLS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"
    };
    
    //The suits according to character
    public static final char[] SUIT_SYMBOLS = { 'd', 'c', 'h', 's' };

    /** The rank. */
    private final int rank;
    
    /** The suit. */
    private final int suit;
    
    /**
     * Constructor rank and suit based on its integer value.
     */
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * Construc card by using string. For ex: 8q
     * @param card
     */
    public Card(String card) {
    	String rankSymbol = card.substring(0, 1);
    	char suitSymbol = card.charAt(1);
    	
    	int rank = -1;
    	
    	for(int i = 0; i < NO_OF_RANKS; i++)
    		if(rankSymbol.equals(RANK_SYMBOLS[i]))
    			rank = i;
    	
    	int suit = -1;
    	
    	for(int i = 0; i < NO_OF_SUITS; i++)
    		if(suitSymbol == SUIT_SYMBOLS[i])
    			suit = i;
    	
    	this.rank = rank;
    	this.suit = suit;
    }
    
    /**
     * Compare if two cards are equal
     */
    public boolean equals(Object others)
    {
        return this == others;
    }
    
    //Return rank value
    public int getRank()
    {
    	return rank;
    }
    
    //Return suit value
    public int getSuit()
    {
    	return suit;
    }
    
    //Assign specific value for a single card
    public int hashCode()
    {
    	return (rank*NO_OF_SUITS + suit);
    }
    
    //Compare two single card by hash codes
    public int compareTo(Card card) {
//        int thisValue = hashCode();
//        int otherValue = card.hashCode();
        int thisValue = this.rank;
        int otherValue = card.rank;
        if (thisValue > otherValue) {
            return -1;
        } else if (thisValue < otherValue) {
            return 1;
        } else 
        {
            return 0;
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() 
    {
        return RANK_SYMBOLS[rank] + SUIT_SYMBOLS[suit];
    }
    
}
