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

    /**
     * set the variables of pot
     */
    public Pot() {
        currentBet = 0;
        potContribution = new HashMap<>();
        totalMoney = 0;
    }

    /**
     * add player's money to pot
     * @param p
     * @param chip
     */
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

    /**
     * get the amount of total money of the players
     * @return
     */
    public int getTotalMoney() {
        return totalMoney;
    }

    /**
     * set total money
     * @param totalMoney
     */
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * set the current bet
     * @param currentBet
     */

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * reset the amount of pot to 0.
     */
    public void reset() {
        currentBet = 0;
        potContribution = new HashMap<>();
        totalMoney = 0;
    }
}
