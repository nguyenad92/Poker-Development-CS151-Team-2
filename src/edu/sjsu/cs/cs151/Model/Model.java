package edu.sjsu.cs.cs151.Model;

import edu.sjsu.cs.cs151.Message.Message;

import java.util.ArrayList;

/**
 * This edu.sjsu.cs.cs151.model.Model class will be the main loop to generate the game
 */
public class Model {
    private ArrayList<Player> playerList;
    private DeckOfCard deckOfCard;
    private Dealer cardDealer;
    private Table table;
    private int dealerPosition = 0, currentActorPosition = 0, bigBlind = 0, noOfActivePlayer;
    private ArrayList<Player> activePlayerList;
    private Player currentActor, dealerPlayer;
    private boolean isFlop, isTurn, isRiver, isStarted, isOver, isEndGame, isShowDown;

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

    /**
     * First State of the game
     */
    public void start() {
        dealerPosition = -1;
        currentActorPosition = -1;

        isStarted = true;
        isEndGame = false;

        resetHand();
    }

    public void dealPreFlop() {
        isFlop = true;
        noOfActivePlayer = activePlayerList.size();
        resetHand();

        setBlind("SMALL");

        nextPlayerToAct();
        setBlind("BIG");

        cardDealer.dealPreFlopCard();
//        nextPlayerToAct();
    }

    public void dealFlop() {
        noOfActivePlayer = activePlayerList.size();
        cardDealer.dealFlopCard();
        nextPlayerToAct();
    }

    public void dealTurn() {
        noOfActivePlayer = activePlayerList.size();
        cardDealer.dealTurnCard();
        nextPlayerToAct();
    }

    public void dealRiver() {
        noOfActivePlayer = activePlayerList.size();
        cardDealer.dealRiverCard();
        nextPlayerToAct();
    }


    public void endGame() {
        table.reset();
        for (Player player : playerList) {
            player.resetHand();
        }
    }

    public void check() {
        noOfActivePlayer--;
        nextPlayerToAct();
    }

    public void call() {
        noOfActivePlayer--;
        int moneyToPay = table.getCurrentBet() - currentActor.getCurrentBet();
        if (moneyToPay > currentActor.getMoney()) moneyToPay = currentActor.getMoney();

        currentActor.payMoney(moneyToPay);
        currentActor.setCurrentBet(moneyToPay);
        table.addMoneyToPot(currentActor, moneyToPay);

        nextPlayerToAct();
    }

    public void bet(int amount) {
        noOfActivePlayer--;
        currentActor.payMoney(amount);
        currentActor.setCurrentBet(amount);
        table.addMoneyToPot(currentActor, amount);
        nextPlayerToAct();
    }

    public void raise(int amount) {
        noOfActivePlayer--;

        int moneyToPay = amount - currentActor.getCurrentBet();

        if (moneyToPay > currentActor.getMoney()) {
            moneyToPay = currentActor.getMoney();
        }
        currentActor.payMoney(moneyToPay);
        currentActor.setCurrentBet(amount);
        table.addMoneyToPot(currentActor, moneyToPay);

        nextPlayerToAct();
    }

    public void fold() {
        noOfActivePlayer--;
        currentActor.resetHand();
        activePlayerList.remove(currentActor);
        currentActorPosition--;

        Player winner = activePlayerList.get(0);
        int amount = table.getTotalMoney();
        winner.addMoney(amount);
        noOfActivePlayer--;

        isOver = true;
        isStarted = false;
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

        if (activePlayerList.size() <= 1) {
            isEndGame = true;
            isStarted = false;
        } else {
            // Rotate the dealer button.
            dealerPosition = (dealerPosition + 1) % activePlayerList.size();
            dealerPlayer = activePlayerList.get(dealerPosition);

            // Determine the first player to act.
            currentActorPosition = dealerPosition;
            currentActor = activePlayerList.get(currentActorPosition);
        }
    }

    public void checkWinner() {
        int bestHandValue = -1;
        isShowDown = false;

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

    public boolean isFlop() {
        return isFlop && noOfActivePlayer == 0;
    }

    public boolean isTurn() {
        return isTurn && noOfActivePlayer == 0;
    }

    public boolean isRiver() {
        return isRiver && noOfActivePlayer == 0;
    }

    public boolean isStarted() {
        return isStarted && noOfActivePlayer == 0;
    }

    public boolean isOver() {
        return isOver && noOfActivePlayer == 0;
    }

    public int getDealerPosition() {
        return dealerPosition;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public int getCurrentActorPosition() {
        return currentActorPosition;
    }

    public Player getCurrentActor() {
        return currentActor;
    }

    public Player getDealerPlayer() {
        return dealerPlayer;
    }

    public ArrayList<Player> getActivePlayerList() {
        return activePlayerList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean isEndGame() {
        return isEndGame && noOfActivePlayer <= 1;
    }

    public boolean isShowDown() {
        return isShowDown && noOfActivePlayer == 0;
    }
    
    public void dealCardByStage() {
        if (isShowDown() && !isEndGame()) {
            checkWinner();
            resetHand();
            dealPreFlop();
        } else if (isFlop()) {
            dealFlop();
            nextPlayerToAct();
            isFlop = false;
            isTurn = true;
        } else if (isTurn()) {
            dealTurn();
            nextPlayerToAct();
            isTurn = false;
            isRiver = true;
        } else if (isRiver()) {
            dealRiver();
            nextPlayerToAct();
            isRiver = false;
            isShowDown = true;
        } else {
            start();
        }
    }
}