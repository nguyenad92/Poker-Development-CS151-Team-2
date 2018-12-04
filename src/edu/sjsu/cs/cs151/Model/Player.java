
package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

/**
 * A class to create player and keep player's information
 * it contains player's actions such as check, call, raise, fold and outGame
 *
 */
public class Player {

	private String id;
	private String name;
	private int level, country, age;
	private String gender;
	private int money; 								// total money of edu.sjsu.cs.cs151.model.Player
	private int currentBet;							// current Bet of the edu.sjsu.cs.cs151.model.Player
	private PlayerHand playerHands;
	private int currentPositionOnTable;
	private String action;

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
	 * do the action to check
	 */
	public void check() {

	}

	public void payMoney(int bet) {
		money = money - bet;
		currentBet = currentBet + bet;
	}
	/**
	 * do the action to fold
	 */
	public void fold() {

	}

	/**
	 * do the action to out the game
	 */
	public void outGame() {

	}

	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public int getMoney() {
		return money;
	}


	public int getCurrentBet() {
		return currentBet;
	}

	public void setCurrentBet(int currentBet) {
		this.currentBet = currentBet;
	}

	public  void resetHand() {
		money = 0;
		currentBet = 0;
		playerHands.getCard().clear();
	}

	public void addMoney(int money) {
		this.money += money;
	}

	public void addCard(ArrayList<Card> cards) {
		playerHands.getCard().addAll(cards);
	}
	
	public ArrayList<Card> getCard()
	{
		return playerHands.getCard();
	}

	public ArrayList<Card> getPlayerHands() {
		return playerHands.getCard();
	}

	public boolean isAllIn() {
		return playerHands.getCard().size() >= 2 && money == 0;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getCurrentPositionOnTable() {
		return currentPositionOnTable;
	}

	public void setCurrentPositionOnTable(int currentPositionOnTable) {
		this.currentPositionOnTable = currentPositionOnTable;
	}
}