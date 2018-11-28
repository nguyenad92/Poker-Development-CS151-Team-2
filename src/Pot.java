import java.util.HashMap;

/**
 *
 * This class will keep track the amount of each player put in
 */
public class Pot {

    private int currentBet;
    private int totalMoney;
    private HashMap<Player, Integer> potContribution;

    public Pot() {
        currentBet = 0;
        potContribution = new HashMap<>();
        totalMoney = 0;
    }

    public void addMoneyToPotContribution(Player p, int chip) {
        int playerCurrentBet;

        if (!potContribution.containsKey(p)) {
            potContribution.put(p, chip);
            playerCurrentBet = chip;
        } else {
            potContribution.put(p, chip + p.getCurrentBet());
            playerCurrentBet = chip + p.getCurrentBet();
        }

        p.setCurrentBet(playerCurrentBet);
        totalMoney += chip;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void reset() {
        currentBet = 0;
        potContribution = new HashMap<>();
        totalMoney = 0;
    }
}
