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
	private PlayerHand[] playerHands;

	/**
	 * Construct a player object
	 */
	public Player(String id, String name, int age, String gender, int money, int level, int country) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.money = money;
		this.level = level;
		this.country = country;
		this.playerHands = new PlayerHand[2];
		currentBet = 0;
	}

	/**
	 * do the action to check
	 */
	public void check() {

	}

	/**
	 * do the action to call
	 */
	public void call() {

	}

	/**
	 * do the action to bet
	 */
	public void bet() {

	}

	/**
	 * do the action to raise
	 */
	public void raise() {

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
		// Clear the PlayerHand
	}

}