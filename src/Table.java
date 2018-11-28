/**
 * A class to show information of players and dealer
 * add and remove players, add money
 * start new game or return cards from the players to the deck
 * announce the winner
 */
public class Table {

    private Pot potContribution;

    public Table() {
        potContribution = new Pot();
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
}
