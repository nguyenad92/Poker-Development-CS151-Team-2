
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to use when game starts.
 * It can shuffle the cards, get the top card from card class.
 */
public class DeckOfCard {
    public static final int LIMIT = 52;
    public ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * Construct a DeckOfCard object
     */
    public DeckOfCard() {
   //     cards = new Card[LIMIT];
//        for (int i = 0; i < LIMIT; i++) {
//            Card card = new Card();
//            this.cards[i] = card;

        }


    /**
     * Create a random deck of cards
     * @return
     */

    public Card shuffleCards() {
        return new Card();
    }

    /**
     * Create a method that takes the top card
     * in the shuffle deck
     */
    public void getTopCard() {

    }


}
