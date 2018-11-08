import java.util.Set;

/**
 * A class to keep cards of players
 *
 */
public class PlayerHand {
    private Player player;
    Set<Card> cards;

    /**
     * Construct a playerHand object
     */
    public PlayerHand(Player player, Set<Card> cards) {
        this.player = player;
        this.cards = cards;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Card> getCard() {
        return cards;
    }

    public void playerHand() {

    }

    public int compareRankHand() {
        return 0;
    }

}
