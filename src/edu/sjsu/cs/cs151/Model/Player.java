package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;
/**
 * A class to create player and keep player's information
 * it contains player's actions such as payMoney, getCard.
 *
 */
public class Player {

	private String name;
	private int money; 								// total money
	private int currentBet;							// current Bet
	private int originalMoney;
	private PlayerHand playerHands;
	private int currentPositionOnTable;
	private String currentAction;

	/**
	 * Construct a player object
	 */
	public Player(String name, int money) {
		this.name = name;
		this.money = money;
		originalMoney = money;
		ArrayList<Card> c = new ArrayList<>();
		this.playerHands = new PlayerHand(this, c);
		currentBet = 0;
		currentPositionOnTable = 0;
	}

	/**
	 * define the current bet
	 * @param bet the amount of new bet
	 */
	public void payMoney(int bet) {
		money = money - bet;
		currentBet = currentBet + bet;
	}

	/**
	 * get player's
	 * @return player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 *name get player's
	 * @return player's money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 *money get the current bet amount
	 * @return current bet
	 */
	public int getCurrentBet() {
		return currentBet;
	}

	/**
	 *
	 * @param currentBet
	 */
	public void setCurrentBet(int currentBet) {
		this.currentBet = currentBet;
	}

	/**
	 * get player's cards back
	 */
	public  void resetHand() {
		currentBet = 0;
		playerHands.getCard().clear();
	}

	/**
	 * add money to player's amount
	 * @param money given
	 */
	public void addMoney(int money) {
		this.money += money;
	}

	/**
	 *
	 * @param cards
	 */
	public void addCard(ArrayList<Card> cards) {
		playerHands.getCard().addAll(cards);
	}

	/**
	 * get cards for the player hand
	 * @return the array of player's cards
	 */
	public ArrayList<Card> getCard()
	{
		return playerHands.getCard();
	}


	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public ArrayList<Card> getPlayerHands() {
		return playerHands.getCard();
	}

	public boolean isAllIn() {
		return playerHands.getCard().size() >= 2 && money == 0;
	}

	public int getCurrentPositionOnTable() {
		return currentPositionOnTable;
	}

	public void setCurrentPositionOnTable(int currentPositionOnTable) {
		this.currentPositionOnTable = currentPositionOnTable;
	}
}