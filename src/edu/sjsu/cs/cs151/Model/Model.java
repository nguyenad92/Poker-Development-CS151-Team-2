package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

/**
 * This edu.sjsu.cs.cs151.model.Model class will be the main loop to generate the game
 */
public class Model {
    private ArrayList<Player> playerList;
    private DeckOfCard deckOfCard;
    private Dealer cardDealer;
    private Table table;
    private int dealerPosition = 0, currentActorPosition = 0, bigBlind = 0;
    private ArrayList<Player> activePlayerList;
    private Player currentActor, dealerPlayer;
    private boolean isFlop, isTurn, isRiver, isStarted, isOver, CheckIfWon;

    public Model() {
        this.table = new Table();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        activePlayerList = new ArrayList<>();
        cardDealer = new Dealer(table, deckOfCard, playerList);
        bigBlind = 100;

        Player p1 = new Player("Calvin Nguyen", 10000);
        Player p2 = new Player("Nhung Le", 10000);
        addPlayer(p1);
        addPlayer(p2);
    }

    public void start() {
        isStarted = true;
        dealerPosition = -1;
        currentActorPosition = -1;

        while (true) {
            for (Player player : playerList) {
                if (player.getMoney() >= 0) {
                    activePlayerList.add(player);
                }
            }
            if (activePlayerList.size() > 1) {
                playHand();
            } else {
                isOver = true;
                isStarted = false;
                break;
            }
        }

        // Reset Everything when oneHand is finished or the other loses all Money
        table.reset();
        for (Player player : playerList) {
            player.resetHand();
        }
    }

    /**
     * Before play a new hand, need to reset everything back to original
     */
    private void playHand() {
        resetHand();

        setBlind("SMALL");

        nextPlayerToAct();
        setBlind("BIG");

        cardDealer.dealPreFlopCard();
        betting();
        isFlop = true;

        while (activePlayerList.size() > 1) {
            table.setCurrentBet(0);
            if (isFlop) {
                cardDealer.dealFlopCard();
                isFlop = false;
                isTurn = true;
            } else if (isTurn) {
                cardDealer.dealTurnCard();
                isTurn = false;
                isRiver = true;
            } else if (isRiver) {
                cardDealer.dealRiverCard();
                isRiver = false;
            } else {
                showDown();
                break;
            }
            betting();
        }
    }

    private void setBlind(String blind) {
        int blindAmount;
        if (blind.equals("BIG")) {
            blindAmount = bigBlind;
        } else {
            blindAmount = bigBlind / 2;
        }
        currentActor.payMoney(blindAmount);
        table.addMoneyToPot(currentActor, blindAmount);
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
        currentActorPosition = dealerPosition;
        currentActor = activePlayerList.get(currentActorPosition);
    }

    public void showDown() {
        int bestHandValue = -1;
        Player winner = activePlayerList.get(0);
        for (Player p : activePlayerList) {
            p.addCard(table.getCommunityCards());
            RankedHand rankedHand = new RankedHand(p);
            if (rankedHand.getRankedHandScore() >= bestHandValue) {
                bestHandValue = rankedHand.getRankedHandScore();
                winner = p;
            }
        }

        winner.addMoney(table.getTotalMoney());
    }

    public void nextPlayerToAct() {
        currentActorPosition = (currentActorPosition + 1) % activePlayerList.size();
        currentActor = activePlayerList.get(currentActorPosition);
    }

    public void betting() {
    	int noOfActivePlayer = activePlayerList.size();
    	
    	while(noOfActivePlayer > 1) {
    		nextPlayerToAct();
            String action = "";
    		//current player ALL IN
    		if(currentActor.isAllIn()) {
                noOfActivePlayer--;
    			table.setCurrentActionStatus("CHECK");
    		} else {
                noOfActivePlayer--;

                // Need to get actions from the GUI
//                action = currentActor.getClient.act(bigBlind, bet, getAllowedAction(currentActor));
                action = currentActor.getAction();

    			//current player CHECK
    			if (action.equals("CHECK")) {
                    //do nothing
                }

    			// current player CALL
    			else if(action.equals("CALL")) {
    				int moneyToPay = table.getCurrentBet() - currentActor.getCurrentBet();
    				if (moneyToPay > currentActor.getMoney()) moneyToPay = currentActor.getMoney();

    				currentActor.payMoney(moneyToPay);
                    currentActor.setCurrentBet(moneyToPay);
    				table.addMoneyToPot(currentActor, moneyToPay);
    			}

    			//current player BET
    			else if(action.equals("BET")) {
                    // Need to set currentBet when user enter amount Input from front end and hit "BET" + set CurrentBet in Player's class
    				int currentBet = table.getCurrentBet();

    				currentActor.payMoney(currentBet);
    				currentActor.setCurrentBet(currentBet);
    				table.addMoneyToPot(currentActor, currentBet);

                    noOfActivePlayer = activePlayerList.size();
    			}

    			//current player RAISE
    			else if(action.equals("RAISE")) {
                    // Need to set currentBet when user enter amount Input from front end and hit "BET" + set CurrentBet in Player's class
                    int currentBet = table.getCurrentBet();

                    int moneyToPay = currentBet - currentActor.getCurrentBet();

                    if (moneyToPay > currentActor.getMoney()) {
                        moneyToPay = currentActor.getMoney();
                    }
                    currentActor.payMoney(moneyToPay);
                    currentActor.setCurrentBet(currentBet);
                    table.addMoneyToPot(currentActor, moneyToPay);
                    noOfActivePlayer = activePlayerList.size();
    			}

    			//current player FOLD
    			else if(action.equals("FOLD")) {
    				currentActor.resetHand();
    				activePlayerList.remove(currentActor);
    				currentActorPosition--;
    				if(activePlayerList.size() == 1) {
    					updateTable();
    					Player winner = activePlayerList.get(0);
    					int amount = table.getTotalMoney();
    					winner.addMoney(amount);
    					updateTable();
    					noOfActivePlayer--;
    				}
    			}
    		}
    		currentActor.setAction(action);
    		if(noOfActivePlayer > 0) {
    			updateTable();
    		}
    	}

    	//reset players' bet
    	for(Player player: activePlayerList) player.setCurrentBet(0);

    	updateTable();
    }
    
    //Notify observer that the tabel has been updated
    private void updateTable() {
    	int pot = table.getTotalMoney();
    	for(Player player: activePlayerList) {}
//    		player.getObserver().tableUpdated(table, bet, pot);
    }


    public ArrayList<String> getAllowedAction(Player player) {
        ArrayList<String> actions = new ArrayList<>();
        if (player.isAllIn()) {
            actions.add("CHECK");
        } else {
            // No one Bet
            if (table.getCurrentBet() == 0) {
                actions.add("CHECK");
                actions.add("BET");
            } else {
                // Someone Bet
                if (currentActor.getCurrentBet() < table.getCurrentBet()) {
                    actions.add("CALL");
                    actions.add("RAISE");
                } else {
                    // Big-blind Action
                    actions.add("CHECK");
                    actions.add("RAISE");
                }
            }
            actions.add("FOLD");
        }
        return actions;
    }

    public boolean isCheckIfWon() {
        return CheckIfWon;
    }

    public boolean isFlop() {
        return isFlop;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public boolean isRiver() {
        return isRiver;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isOver() {
        return isOver;
    }
}