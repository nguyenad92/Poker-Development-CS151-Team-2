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
    private int dealerPosition, currentActorPosition, bigBlind, noOfActivePlayer, bigBlindPosition;
    private ArrayList<Player> activePlayerList;
    private Player currentActor, dealerPlayer, bigBlindPlayer, winner;
    private boolean isFlop, isTurn, isRiver, isStarted, isOver, isShowDown, hasWinner;

    /**
     * Define player's info and add players
     */
    public Model() {

    }

    /**
     * First State of the game
     */
    public void start() {
        table       = new Table();
        deckOfCard  = new DeckOfCard();
        playerList  = new ArrayList<>();

        activePlayerList = new ArrayList<>();
        bigBlind = 2000;

        Player p1 = new Player("Calvin Nguyen", 10000);
        Player p2 = new Player("Nhung Le", 10000);

        addPlayer(p1);
        addPlayer(p2);

        cardDealer = new Dealer(table, deckOfCard, playerList);

        dealerPosition = -1;
        currentActorPosition = -1;
        bigBlindPosition = -1;

        isStarted = true;
        isOver = false;
        hasWinner = false;
    }

    /**
     * First round of the game, deal 2 cards to each player
     * Set blind for specific player
     */
    public void dealPreFlop() {

        noOfActivePlayer = activePlayerList.size();

        isFlop = true;
        isStarted = false;
        hasWinner = false;

        setBlind("SMALL");

        nextPlayerToAct();
        setBlind("BIG");

        cardDealer.dealPreFlopCard();
    }

    /**
     * Round 2nd of the game, deal 3 cards on the community cards
     */
    public void dealFlop() {

        table.setCurrentBet(0);
        for(Player p : activePlayerList) {
            p.setCurrentBet(0);
            p.setCurrentAction(" ");
        }

        cardDealer.dealFlopCard();
    }
    
    /**
     * Round 3rd of the game, deal 1 card on the community card
     */
    public void dealTurn() {
//        noOfActivePlayer = activePlayerList.size();
        table.setCurrentBet(0);
        for(Player p : activePlayerList) {
            p.setCurrentBet(0);
            p.setCurrentAction(" ");
        }
        cardDealer.dealTurnCard();
    }

    /**
     * Last round of the game, deal last card on the community card
     */
    public void dealRiver() {
//        noOfActivePlayer = activePlayerList.size();
        table.setCurrentBet(0);
        for(Player p : activePlayerList) {
            p.setCurrentBet(0);
            p.setCurrentAction(" ");
        }
        cardDealer.dealRiverCard();
    }

    /**
     * player's action to not doing anything
     */
    public void check() {
        noOfActivePlayer--;
        System.out.println(currentActor.getName() + " just check");
        currentActor.setCurrentAction("CHECK");
    }

    public void call() {
        noOfActivePlayer--;
        System.out.println("Num of Active Player: " + noOfActivePlayer);
        currentActor.setCurrentAction("CALL");
        int moneyToPay = table.getCurrentBet() - currentActor.getCurrentBet();
        if (moneyToPay > currentActor.getMoney()) moneyToPay = currentActor.getMoney();


        table.addMoneyToPot(currentActor, moneyToPay);
    }

    /**
     * player enters the amount of bet
     * take the player's money bet amount to pot.
     * @param amount
     */
    public void bet(int amount) {
        noOfActivePlayer--;
        if (noOfActivePlayer == 0) noOfActivePlayer++;
        currentActor.setCurrentAction("BET");
        int moneyToPay = amount;

        if (moneyToPay > currentActor.getMoney()) {
            moneyToPay = currentActor.getMoney();
        }

        table.addMoneyToPot(currentActor, moneyToPay);
    }

    /**
     * player raise the bet amount
     * take the player's money bet amount to pot.
     * @param amount
     */
    public void raise(int amount) {
        noOfActivePlayer--;
        System.out.println("Num of Active Player when raise: " + noOfActivePlayer);
        if (noOfActivePlayer == 0) noOfActivePlayer++;
        currentActor.setCurrentAction("RAISE");
        int moneyToPay = amount - currentActor.getCurrentBet();

        if (moneyToPay > currentActor.getMoney()) {
            moneyToPay = currentActor.getMoney();
        }
        table.addMoneyToPot(currentActor, moneyToPay);
    }

    /**
     * discard the player hand
     * take the player's cards back and the other player win
     */
    public void fold() {
        noOfActivePlayer--;
        currentActor.setCurrentAction("FOLD");
        currentActor.resetHand();

        activePlayerList.remove(currentActor);

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
        if (blindAmount > currentActor.getMoney()) blindAmount = currentActor.getMoney();
        currentActor.setCurrentAction(blind + "BLIND");
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

    /**
     * get player's cards back
     */
    public void resetHand() {
        table.reset();
        deckOfCard.shuffle(); // Shuffle the deck.

        activePlayerList.clear();
        for (Player player : playerList) {
            player.resetHand();
            if (player.getMoney() > 0) {
                activePlayerList.add(player);
            }
        }

        if (activePlayerList.size() <= 1) {
            isOver = true;
            isStarted = false;
        } else if (!isOver) {
            // Rotate the dealer button.
            isStarted = true;
            hasWinner = false;
            dealerPosition = (dealerPosition + 1) % activePlayerList.size();

            bigBlindPosition = (dealerPosition + 1) % activePlayerList.size();

            dealerPlayer = activePlayerList.get(dealerPosition);
            bigBlindPlayer = activePlayerList.get(bigBlindPosition);

            // Determine the first player to act.
            currentActorPosition = dealerPosition;
            currentActor = activePlayerList.get(currentActorPosition);
        }
    }

    /**
     * compare 2 set cards and define winner
     */
    public void checkWinner() {
        noOfActivePlayer--;
        int bestHandValue = -1;
        isShowDown = false;
        winner = activePlayerList.get(0);

        for (Player p : activePlayerList) {
            p.addCard(table.getCommunityCards());

//            System.out.println("Card of this player: " + p.getPlayerHands().toString());
            RankedHand rankedHand = new RankedHand(p);
//            System.out.println(p.getName() + " : score = " +  rankedHand.getRankedHandScore() +
//                    " type Hand: " + rankedHand.getRankedHandType().getHandType());
//            System.out.println();

            if (rankedHand.getRankedHandScore() >= bestHandValue) {
                bestHandValue = rankedHand.getRankedHandScore();
                winner = p;
                winner.setWinner();
            }
        }
        winner.addMoney(table.getTotalMoney());
    }

    /**
     * get the turn of the 2 players go back and forth
     */
    public void nextPlayerToAct() {
        currentActorPosition = (currentActorPosition + 1) % activePlayerList.size();
        currentActor = activePlayerList.get(currentActorPosition);
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
        return isStarted;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setIsOver(boolean status ){
        isOver = status;
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

    public Player getBigBlindPlayer() {
        return bigBlindPlayer;
    }

    public boolean isShowDown() {
        return isShowDown && noOfActivePlayer == 0;
    }

    public void dealCardByStage() {
        if ((isShowDown() && !isOver)) {
            checkWinner();
            isStarted = true;
            hasWinner = true;
            isOver = false;
        } else {
            if (isFlop()) {
                System.out.println("Just Deal Flop");
                dealFlop();
                isFlop = false;
                isTurn = true;
            } else if (isTurn()) {
                System.out.println("Just Deal Turn");
                dealTurn();
                isTurn = false;
                isRiver = true;
            } else if (isRiver()) {
                System.out.println("Just Deal River");
                dealRiver();
                isRiver = false;
                isShowDown = true;
            }

            if (isFlop() || isTurn() || isRiver() || isShowDown()) {
                currentActorPosition = dealerPosition;
                currentActor = playerList.get(currentActorPosition);
                noOfActivePlayer = activePlayerList.size();

            } else {
                nextPlayerToAct();
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public int getNoOfActivePlayer() {
        return noOfActivePlayer;
    }

    public void setFlop(boolean flop) {
        isFlop = flop;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public void setRiver(boolean river) {
        isRiver = river;
    }

    public void setShowDown(boolean showDown) {
        isShowDown = showDown;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Player getWinner() {
        return winner;
    }
}