package edu.sjsu.cs.cs151.model;

import javax.swing.*;
import java.util.ArrayList;
/**
 * This edu.sjsu.cs.cs151.model.PokerGame class will be the main loop to generate the game
 */
public class PokerGame {

    private ArrayList<Player> playerList;
    private DeckOfCard deckOfCard;
    private Dealer cardDealer;
    private Table table;
    private int dealerPosition = 0, currentPlayerPosition = 0, bigBlind = 0;
    private RankedHandChecker handComparison;
    private ArrayList<Player> activePlayerList;
    private Player currentPlayerToAct;
    private boolean isFlop, isTurn, isRiver;
    private Player actor, dealerPlayer;
    private int bet, raises;

    public PokerGame(Player p1, Player p2, int bigBlind) {
        this.table = new Table();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        activePlayerList = new ArrayList<>();
        cardDealer = new Dealer(table, deckOfCard, playerList);
        this.bigBlind = bigBlind;

        // Add Player
        addPlayer(p1);
        addPlayer(p2);
    }

    private void start() {
        dealerPosition = -1;
        currentPlayerPosition = -1;

        while (true) {
            for (Player player : playerList) {
                if (player.getMoney() >= 0) {
                    activePlayerList.add(player);
                }
            }
            if (activePlayerList.size() > 1) {
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
        if (activePlayerList.size() > 1) {
            rotatePosition();
        }

        currentPlayerToAct.setBlind(bigBlind);


        deckOfCard.shuffle();                           // Deck Shuffle
        cardDealer.dealPreFlopCard();                  // Deck deal Preflop
        betting();

        while (activePlayerList.size() > 1) {
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
        deckOfCard.shuffle(); // Shuffle the deck.
        
        activePlayerList.clear();
        for (Player player : playerList) {
            player.resetHand();
            if (player.getMoney() >= 0) {
                activePlayerList.add(player);
            }
        }

        // Rotate the dealer button.
        dealerPosition = (dealerPosition + 1) % activePlayerList.size();
        dealerPlayer = activePlayerList.get(dealerPosition);

        // Determine the first player to act.
        currentPlayerToAct.setCurrentPositionOnTable(dealerPosition);
        actor = activePlayerList.get(currentPlayerToAct.getCurrentPositionOnTable());
    }

    public void showDown() {

    }

    public void rotatePosition() {
        dealerPosition++;
        int playerPostion = currentPlayerToAct.getCurrentPositionOnTable();
        playerPostion = (playerPostion + 1) % activePlayerList.size();
        actor = activePlayerList.get(playerPostion);
    }


    public int getDealerPosition() {
        return dealerPosition;
    }

    public void betting() {
    	int playerToAct = playerList.size();
    	if (table.sizeCard() == 0) {
    		bet = bigBlind;
    	}
    	
    	else {
    		currentPlayerPosition = dealerPosition;
    		bet = 0;
    	}
    	
    	if(playerToAct == 2) {
    		currentPlayerPosition = dealerPosition;
    	}
    	
    	lastBettor = null;
    	raises = 0;
    	updateTable();
    	
    	while(playerToAct > 0) {
    		rotatePosition();
    		Action action = null;
    		
    		//current player ALL IN
    		if(actor.isALlIn) {
    			action = Action.CHECK;
    			playerToAct--;
    		}
    		else {
    			playerToAct--;
    			//current player CHECK
    			if(table.getCurrentActionStatus().equals("CHECK")) {} //do nothing

    			//current player CALL
    			if(table.getCurrentActionStatus().equals("CALL")) {
    				int betIncrement = table.getTotalMoney() + actor.getCurrentBet();
    				if(betIncrement > actor.getMoney())
    					betIncrement = actor.getMoney();
    				actor.setCurrentBet(actor.getCurrentBet() - betIncrement);
    				table.addMoneyToPot(actor, actor.getCurrentBet());
    			}
    			//current player BET
    			if(table.getCurrentActionStatus().equals("BET")) {
    				int amount = table.getCurrentBet();
    				actor.payMoney(amount - actor.getCurrentBet());
    				table.addMoneyToPot(actor, actor.getCurrentBet());
    				bet = amount;
    				playerToAct = playerList.size();
    			}
    			//current player RAISE
    			if(table.getCurrentActionStatus().equals("RAISE")) {
    				int amount = table.getCurrentBet();
    			}

    			//current player FOLD
    			if(table.getCurrentActionStatus().equals("FOLD")) {
    				actor.resetHand();
    				activePlayerList.remove(actor);
    				currentPlayerPosition--;
    				if(activePlayerList.size() == 1) {
    					updateTable();
    					nextPlayerToAct();
    					Player winner = activePlayerList.get(0);
    					int amount = table.getTotalMoney();
    					winner.addMoney(amount);
    					updateTable();
    					playerToAct = 0;
    				}
    			}
    				
    		}
    		if(playerToAct > 0) {
    			updateTable();
    			nextPlayerToAct();
    		}
    	}
    	//reset players' bet
    	for(Player player: activePlayerList)
    		player.setCurrentBet(0);
    	updateTable();
    	
    }
    
    //Notify observer that the tabel has been updated
    private void updateTable()
    {
    	int pot = table.getTotalMoney();
    	for(Player player: activePlayerList)
    		player.getObserver().tableUpdated(table, bet, pot);
    }

    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public void getAllowedAction(String action) {

    }
}