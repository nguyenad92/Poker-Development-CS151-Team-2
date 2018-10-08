/**
 * A class will store the suits and numbers for a card
 */
public class Card {
    public Value value;
    public Suits suit;

    /**
     * Construct a card object
     */
    public Card(Suits suit, Value value) {
        this.value = value;
        this.suit = suit;
    }

    /**
     * Get the value of a card.
     * @return its value.
     */
    public int getValue(){

        return value;
    }

    /**
     * Get the suit of a card.
     * @return its suit.
     */

    public String getSuit(){

        return suit;
    }


}
