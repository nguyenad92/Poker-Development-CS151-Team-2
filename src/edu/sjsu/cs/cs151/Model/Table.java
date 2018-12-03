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

    public Table() {
        potContribution = new Pot();
        communityCards = new ArrayList<>();
    }
    
    
    public void addCard(Card c)
    {
    	communityCards.add(c);
    }
    
    public Card showCommunityCards(int i)
    {
    	return communityCards.get(i);
    }
    
    public int sizeCard()
    {
    	return communityCards.size();
    }

    public void addMoneyToPot(Player p, int betMoney) {
        potContribution.addMoneyToPotContribution(p, betMoney);
    }

    public Pot getPotContribution() {
        return potContribution;
    }

    public int getTotalMoney() {
        return potContribution.getTotalMoney();
    }

    public void reset() {
        potContribution.reset();
        communityCards.clear();
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    public int getCurrentBet() {
        return potContribution.getCurrentBet();
    }

    public String getCurrentActionStatus() {
        return currentActionStatus;
    }

    public void setCurrentActionStatus(String currentActionStatus) {
        this.currentActionStatus = currentActionStatus;
    }

    public void setCurrentBet(int money) {
        potContribution.setCurrentBet(money);
    }
}
