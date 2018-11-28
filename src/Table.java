import java.util.*;
/**
 * A class to show information of players and dealer
 * add and remove players, add money
 * start new game or return cards from the players to the deck
 * announce the winner
 */
public class Table {

    private Pot potContribution;
    private List<Card> cards;
    private String actionStatus;


    
    public Table() {
        potContribution = new Pot();
        cards = new ArrayList<>();
    }
    
    
    public void addCard(Card c)
    {
    	cards.add(c);
    }
    
    public Card showCards(int i)
    {
    	return cards.get(i);
    }
    
    public int sizeCard()
    {
    	return cards.size();
    }

    public void addMoneyToPot(Player p, int betMoney) {
        if (betMoney > potContribution.getCurrentBet()) {               // User Raise --> Set new currentBet
            potContribution.setCurrentBet(betMoney);
        }
        potContribution.addMoneyToPotContribution(p, betMoney);
    }

    public Pot getPotContribution() {
        return potContribution;
    }

    public void reset() {
        potContribution.reset();
        cards.clear();
    }

    public void setCurrentBet() {
        potContribution.setCurrentBet(0);
    }
}
