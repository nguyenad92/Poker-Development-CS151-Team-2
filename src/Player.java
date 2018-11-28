import java.util.Set;

/**
 * A class to create player and keep player's information
 * it contains player's actions such as check, call, raise, fold and outGame
 *
 */
public class Player {

	private String id;
	private String name;
	private int age;
	private String gender;
	private int money; 								// total money of Player
	private int currentBet;							// current Bet of the Player
	private int level;
	private int country;
	private PlayerHand playerHands;
	private boolean isWin;
	private Set<Card> cards;

	/**
	 * Construct a player object
	 */
	public Player(String id, String name, int age, String gender, int money, int level, int country, boolean isWin, Set<Card> c) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.money = money;
		this.level = level;
		this.country = country;
		cards = c;
		this.playerHands = new PlayerHand(c);
		this.isWin = isWin;
		currentBet =0;
	}

	/**
	 * do the action to check
	 */
	public void check() {

	}

	public void payMoney(int money) {
		money =
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

	// getMoney has to be int


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
		setCard().clear();

		// Clear the PlayerHand
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean win) {
		isWin = win;
	}

	public void setMoney(int money) {
		int newMoney = getMoney() + money;
		this.money = newMoney;
	}

	public void setCard(Set<Card> cards) {
		for (int i = 0; i < 7; i++) {
			Set<Card>[i] = playerHands.getCard();
		}
	}

	public void betting(int bet) {
		currentBet = bet + currentBet;
	}

}