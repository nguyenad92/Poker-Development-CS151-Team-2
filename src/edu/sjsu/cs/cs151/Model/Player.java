package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;
/**
 * A class to create player and keep player's information
 * it contains player's actions such as payMoney, getCard.
 *
 */
public class Player {

	/**
	 * Instance variable of the Plaer
	 */
	private String name;
	private int money; 								// total money
	private int currentBet;							// current Bet
	private PlayerHand playerHands;
	private int currentPositionOnTable;
	private String currentAction;
	private boolean isWinner;

	private RankedHand rankedHand;

	/**
	 * Construct a player object
	 */
	public Player(String name, int money) {
		this.name = name;
		this.money = money;
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
	 * money get the current bet amount
	 * @return current bet
	 */
	public int getCurrentBet() {
		return currentBet;
	}

	/**
	 * Set the bet of the player
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
		isWinner = false;
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


	/**
	 * Setter and getter of the class
	 * @param currentAction
	 */
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public ArrayList<Card> getPlayerHands() {
		return playerHands.getCard();
	}

	public void setWinner(){isWinner = true;}

	public void setRankedHand(RankedHand rankedHand) {
		this.rankedHand = rankedHand;
	}

	public RankedHand getRankedHand() {
		return rankedHand;
	}
}