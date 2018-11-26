import java.util.HashMap;

/**
 *
 * This class will keep track the amount of each player put in
 */
public class Pot {

    private int currentBet;

    private HashMap<Player, Integer> potContribution;

    public Pot() {
        currentBet = 0;
        potContribution = new HashMap<>();

    }

    public void addMoneyToPotContribution(Player p, int chip) {

    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getCurrentBet() {
        return currentBet;
    }
}
