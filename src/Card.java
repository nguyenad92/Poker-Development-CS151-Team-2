/**
 * A class will store the suits and numbers for a card
 */
public class Card {
    public Value value;
    public Suits suit;
    public enum Suits{SPADES, HEART, CLUBS, DIMONDS};
    public enum Value{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K" , "A"};

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
