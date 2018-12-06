package edu.sjsu.cs.cs151.Model;

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
        if (!potContribution.containsKey(p)) {
            potContribution.put(p, chip);
        } else {
            potContribution.put(p, chip + p.getCurrentBet());
        }
        currentBet = chip + p.getCurrentBet();
        p.payMoney(chip);
        totalMoney += chip;
    }

    public int getTotalMoney() {
        return totalMoney;
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
