import java.util.ArrayList;

/**
 * A class to store the rank of the card
 */
public class RankedHand {

    private ArrayList<Card> cardToBeAnalyzed = new ArrayList<>();


    public RankedHand(ArrayList<Card> card) {
        cardToBeAnalyzed.addAll(card);
    }

    // Check for High Card

    // Check for 1 Pair

    // Check for 2 Pairs

    // Check for a Set

    // Check for a straight

    // check for a flush

    // check for a full house

    // check for 4 of a Kind

    // Check for straight flush

    // Check for Royal Flush


    public ArrayList<Card> getCardToBeAnalyzed() {
        return cardToBeAnalyzed;
    }

    public void setCardToBeAnalyzed(ArrayList<Card> cardToBeAnalyzed) {
        this.cardToBeAnalyzed = cardToBeAnalyzed;
    }
}
