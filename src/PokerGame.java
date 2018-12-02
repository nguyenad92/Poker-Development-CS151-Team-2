import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * This PokerGame class will be the main loop to generate the game
 */
public class PokerGame {

    private ArrayList<Player> playerList;
    private DeckOfCard deckOfCard;
    private Dealer cardDealer;
    private Table table;
    private int dealerPosition = 0, currentPlayerPosition = 0, bigBlind = 0;
    private RankedHand handComparison;
    private ArrayList<Player> activePlayer;
    private Player currentPlayerToAct;
    private boolean isFlop, isTurn, isRiver;
    private Player actor, dealerPlayer;

    public PokerGame(Player p1, Player p2, int bigBlind) {
        this.table = new Table();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        activePlayer = new ArrayList<>();
        cardDealer = new Dealer(table, deckOfCard, playerList);
        this.bigBlind = bigBlind;

        // Add Player
        addPlayer(p1);
        addPlayer(p2);
    }


    /**
     * Main Function will run the whole program
     *
     * @param args
     */
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//
//        JTextArea area = new JTextArea(20, 40); // 20 rows, 40 columns
//        JScrollPane scroller = new JScrollPane(area);
//        frame.add(scroller, BorderLayout.CENTER);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(200, 200);
//        frame.setVisible(true);

//        PokerGame main = new PokerGame();
    }

    private void start() {
        dealerPosition = -1;
        currentPlayerPosition = -1;

        while (true) {
            int noOfActivePlayers = 0;
            for (Player player : playerList) {
                if (player.getMoney() >= 0) {
                    noOfActivePlayers++;
                }
            }
            if (noOfActivePlayers > 1) {
                playHand();
            } else {
                break;
            }
        }

        // Reset Everything when oneHand is finished or the other loses all Money
        table.reset();
        for (Player player : playerList) {
            player.resetHand();
        }
        System.out.println("No one is at the table OR others don't have enough money to play");
    }


    /**
     * Before play a new hand, need to reset everything back to original
     */
    private void playHand() {
        resetHand();

        // Set BigBlind and Small Blind
        if (activePlayer.size() > 1) {
            rotatePosition();
        }

        currentPlayerToAct.setBlind(bigBlind);


        deckOfCard.shuffle();                           // Deck Shuffle
        cardDealer.dealPreFlopCard();                  // Deck deal Preflop
        betting();

        while (activePlayer.size() > 1) {
            table.setCurrentBet();
            if (isFlop) {
                cardDealer.dealFlopCard();
            } else if (isTurn) {
                cardDealer.dealTurnCard();
            } else if (isRiver) {
                cardDealer.dealRiverCard();
            }
            betting();
            if (isRiver) showDown();
        }
    }

    /**
     * Adds a player.
     * @param player
     *            The player.
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }


    public void resetHand() {
        table.reset();
        deckOfCard.shuffle();                           // Shuffle the deck.

//        notifyBoardUpdated();

        activePlayer.clear();
        for (Player player : playerList) {
            player.resetHand();
            if (player.getMoney() >= 0) {
                activePlayer.add(player);
            }
        }

        // Rotate the dealer button.
        dealerPosition = (dealerPosition + 1) % activePlayer.size();
        dealerPlayer = activePlayer.get(dealerPosition);


        // Determine the first player to act.
        currentPlayerToAct.setCurrentPositionOnTable(dealerPosition);
        actor = activePlayer.get(currentPlayerToAct.getCurrentPositionOnTable());

        // Set the initial bet to the big blind.
        minBet = bigBlind;
        bet = minBet;
    }

    public void showDown() {

    }

    public void rotatePosition() {
        dealerPosition++;
        int playerPostion = currentPlayerToAct.getCurrentPositionOnTable();
        playerPostion = (playerPostion + 1) % activePlayer.size();
        actor = activePlayer.get(playerPostion);
    }


    public int getDealerPosition() {
        return dealerPosition;
    }

    public void betting()
    {
    	int playerToAct = playerList.size();
    	if (table.sizeCard() == 0)
    	{
    		bet = bigBlind;
    	}
    	
    	else {
    		currentPlayerPosition = dealerPosition;
    		bet = 0;
    	}
    	
    	if(playerToAct == 2)
    	{
    		currentPlayerPosition = dealerPosition;
    	}
    	
    	lastBettor = null;
    	raises = 0;
    	updateTable();
    	
    	while(playerToAct > 0)
    	{
    		changeActor();
    		Action action = null;
    		if(actor.isALlIn)
    		{
    			action = Action.CHECK;
    			playerToAct--;
    		}
    		else
    		{
    			playerToAct--;
    			if(table.getStatus == check())
    				{} //do nothing
    			if(table.getStatus == call)
    			{
    				int betIncrement = pot.getCurrentBet() - actor.getBet;
    				if(betIncrement > actor.getMoney())
    					betIncrement = actor.getMoney();
    				actor.setBet(actor.getBet() = betIncrement);
    				pot.addMoneyToPotContribution(actor, actor.getBet());
    			}
    			if(table.getStatus = bet())
    			{
    				int amount = pot.getCurrentBet();
    				actor.setBet(actor.getBet() = betIncrement);
    				pot.addMoneyToPotContribution(actor, actor.getBet());
    				
    				
    			}
    				
    				
    		}
    	}
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public void getAllowedAction(String action) {

    }
}