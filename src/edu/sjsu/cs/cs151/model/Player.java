
package edu.sjsu.cs.cs151.model;

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
	private boolean allIn;

	/**
	 * Construct a player object
	 */
	public Player(String id, String name, int age, String gender, int money, int level, int country, boolean isWin, ArrayList<Card> c) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.money = money;
		this.level = level;
		this.country = country;
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

	public void setBlind(int blind) {
		currentBet += blind;
		money -= blind;
	}

	public ArrayList<Card> getPlayerHands() {
		return playerHands.getCard();
	}


	public boolean isAllIn() {
		return allIn;
	}

	public int getCurrentPositionOnTable() {
		return currentPositionOnTable;
	}

	public void setCurrentPositionOnTable(int currentPositionOnTable) {
		this.currentPositionOnTable = currentPositionOnTable;
	}
}