package edu.sjsu.cs.cs151.Model;


import java.util.ArrayList;
import java.util.Random;

/**
 * A standard, generic deck of game cards
 
 */
public class DeckOfCard {
    
    /** The number of cards in a deck = 52. */
    private static final int NO_OF_CARDS = Card.NO_OF_RANKS * Card.NO_OF_SUITS;
    
    /** The cards in the deck. */
    private Card[] cards;
    
    /** The index of the next card to deal. */
    private int nextCard = 0;
    
    /** Random number generator  */
    private Random random = new Random();

    /**
     * Constructor.
     */
    public DeckOfCard() 
    {
        cards = new Card[NO_OF_CARDS];
        int index = 0;
        for (int suit = Card.NO_OF_SUITS - 1; suit >= 0; suit--) 
            for (int rank = Card.NO_OF_RANKS - 1; rank >= 0 ; rank--) 
                cards[index++] = new Card(rank, suit);        
    }
    
    /**
     * Shuffles the deck.
     */
    public void shuffle() 
    {
    	for(int oldValue = 0; oldValue < NO_OF_CARDS; oldValue++)
    	{
    		int newValue = random.nextInt(NO_OF_CARDS);
    		Card tempCard = cards[oldValue];
    		cards[oldValue] = cards[newValue];
    		cards[newValue] = tempCard;
    	}
       
    	nextCard = 0;
    }

    /**
     * Resets the deck.
     * 
     */
    public void reset() 
    {
        nextCard = 0;
    }
    
    /**
     * Deals a single card.
     */
    public Card deal() 
    {
        return cards[nextCard++];
    }
    
    /**
     * Deals multiple cards at once or to deal community cards.
     */
    public ArrayList<Card> deal(int noOfCards) 
    {
        ArrayList<Card> communityCards = new ArrayList<Card>();
        for (int i = 0; i < noOfCards; i++) {
        	communityCards.add(cards[nextCard++]);
        }
        return communityCards;
    }
    
    /** to string value of cards */
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) 
        {
            sb.append(card);
            sb.append(' ');
        }
        return sb.toString().trim();
    }
    
}