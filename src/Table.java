import java.util.*;
/**
 * A class to show information of players and dealer
 * add and remove players, add money
 * start new game or return cards from the players to the deck
 * announce the winner
 */
public class Table {

    private int totalMoney;
    private Pot potContribution;
    public DeckOfCard deck;
    public ArrayList<Player> player;
    
    public Table()
    {
    	deck = new DeckOfCard();
    	player = new ArrayList<>();
    }
    
    /**
     * deal two cards to players
     */
    
    public void dealPreFlopCard()
    {
    	for(int i = 0; i < player.size(); i++)
    		player.get(i).addCard(deck.deal(2));
    }

    public Table() {
        potContribution = new Pot();
        this.totalMoney = 0;
    }

    public void addMoneyToPot(Player p, int betMoney) {
        potContribution.setCurrentBet(potContribution.getCurrentBet() + betMoney);
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public Pot getPotContribution() {
        return potContribution;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
