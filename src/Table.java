/**
 * A class to show information of players and dealer
 * add and remove players, add money
 * start new game or return cards from the players to the deck
 * announce the winner
 */
public class Table {

    private int totalMoney;
    private Pot potContribution;

    public Table() {
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
}
