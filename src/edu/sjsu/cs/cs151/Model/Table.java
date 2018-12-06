package edu.sjsu.cs.cs151.Model;

import java.util.*;
/**
 * A class to show information of players and dealer
 * add and remove players, add money
 * start new game or return communityCards from the players to the deck
 * announce the winner
 */
public class Table {

    private Pot potContribution;
    private ArrayList<Card> communityCards;
    private String currentActionStatus;

    /**
     * define table
     */
    public Table() {
        potContribution = new Pot();
        communityCards = new ArrayList<>();
    }

    /**
     * add card to from the deck of card
     * @param c
     */
    public void addCard(ArrayList<Card> c) {
            communityCards.addAll(c);
    }

    /**
     * display the community cards
     * @param i
     * @return
     */
    public Card showCommunityCards(int i) {
    	return communityCards.get(i);
    }

    /**
     * check for the size of card
     * @return
     */
    public int sizeCard() {
    	return communityCards.size();
    }

    /**
     * add money to the pot
     * @param p
     * @param betMoney
     */
    public void addMoneyToPot(Player p, int betMoney) {
        potContribution.addMoneyToPotContribution(p, betMoney);
    }

    /**
     * get the pot contribution
     * @return
     */
    public Pot getPotContribution() {
        return potContribution;
    }

    /**
     * get the total money
     * @return
     */
    public int getTotalMoney() {
        return potContribution.getTotalMoney();
    }

    /**
     * reset the table
     */
    public void reset() {
        potContribution.reset();
        communityCards.clear();
    }

    /**
     * get the community cards from the deck of card
     * @return
     */
    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     * get current bet amount
     * @return
     */
    public int getCurrentBet() {
        return potContribution.getCurrentBet();
    }

    /**
     * check the current active status
     * @return
     */
    public String getCurrentActionStatus() {
        return currentActionStatus;
    }

    /**
     * set the active status
     * @param currentActionStatus
     */
    public void setCurrentActionStatus(String currentActionStatus) {
        this.currentActionStatus = currentActionStatus;
    }

    /**
     * set the current bet amount
     * @param money
     */
    public void setCurrentBet(int money) {
        potContribution.setCurrentBet(money);
    }
}
