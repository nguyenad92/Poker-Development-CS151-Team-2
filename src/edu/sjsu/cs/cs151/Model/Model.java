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
    private int dealerPosition = 0, currentActorPosition = 0, bigBlind = 0, noOfActivePlayer, bigBlindPosition = -1;
    private ArrayList<Player> activePlayerList;
    private Player currentActor, dealerPlayer, bigBlindPlayer;
    private boolean isFlop, isTurn, isRiver, isStarted, isOver, isEndGame, isShowDown;

    public Model() {
        this.table = new Table();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        activePlayerList = new ArrayList<>();
        bigBlind = 2000;

        Player p1 = new Player("Calvin Nguyen", 3000);
        Player p2 = new Player("Nhung Le", 3000);
        addPlayer(p1);
        addPlayer(p2);

        cardDealer = new Dealer();
    }

    /**
     * First State of the game
     */
    public void start() {
        dealerPosition = -1;
        currentActorPosition = -1;

        isStarted = true;
        isOver = false;

        resetHand();
    }

    /**
     * First round of the game, deal 2 cards to each player
     * Set blind for specific player
     */
    public void dealPreFlop() {
        isFlop = true;
        table.reset();
        noOfActivePlayer = activePlayerList.size();

        isStarted = false;

        setBlind("SMALL");

        nextPlayerToAct();
        setBlind("BIG");

        cardDealer.dealPreFlopCard();
    }

    /**
     * Round 2nd of the game, deal 3 cards on the community cards
     */
    public void dealFlop() {
        noOfActivePlayer = activePlayerList.size();
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
        noOfActivePlayer = activePlayerList.size();
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
        noOfActivePlayer = activePlayerList.size();
        table.setCurrentBet(0);
        for(Player p : activePlayerList) {
            p.setCurrentBet(0);
            p.setCurrentAction(" ");
        }
        cardDealer.dealRiverCard();
    }

    public void check() {
        noOfActivePlayer--;
        currentActor.setCurrentAction("CHECK");
    }

    public void call() {
        noOfActivePlayer--;
        currentActor.setCurrentAction("CALL");
        int moneyToPay = table.getCurrentBet() - currentActor.getCurrentBet();
        if (moneyToPay > currentActor.getMoney()) moneyToPay = currentActor.getMoney();

        table.addMoneyToPot(currentActor, moneyToPay);
    }

    public void bet(int amount) {
        noOfActivePlayer--;

        currentActor.setCurrentAction("BET");
        int moneyToPay = amount - currentActor.getCurrentBet();

        System.out.println(currentActor.getMoney());
        if (moneyToPay > currentActor.getMoney()) {
            moneyToPay = currentActor.getMoney();
        }
        table.addMoneyToPot(currentActor, moneyToPay);
    }

    public void raise(int amount) {
        noOfActivePlayer--;

        currentActor.setCurrentAction("RAISE");
        int moneyToPay = amount - currentActor.getCurrentBet();

        if (moneyToPay > currentActor.getMoney()) {
            moneyToPay = currentActor.getMoney();
        }
        table.addMoneyToPot(currentActor, moneyToPay);
    }

    public void fold() {
        noOfActivePlayer--;
        currentActor.setCurrentAction("FOLD");
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
            return;
        } else if (!isOver) {
            // Rotate the dealer button.
            isStarted = true;
            dealerPosition = (dealerPosition + 1) % activePlayerList.size();

            bigBlindPosition = (dealerPosition + 1) % activePlayerList.size();

            dealerPlayer = activePlayerList.get(dealerPosition);
            bigBlindPlayer = activePlayerList.get(bigBlindPosition);

            // Determine the first player to act.
            currentActorPosition = dealerPosition;
            currentActor = activePlayerList.get(currentActorPosition);

            System.out.println("Dealer pos: " + dealerPosition);
            System.out.println("Big pos: " + bigBlindPosition);
            System.out.println("Cur pos: " + currentActorPosition);
        }
    }

    public void checkWinner() {
        noOfActivePlayer--;
        int bestHandValue = -1;
        isShowDown = false;
        Player winner = activePlayerList.get(0);

        for (Player p : activePlayerList) {
            p.addCard(table.getCommunityCards());

            System.out.println("Card of this player: " + p.getPlayerHands().toString());
            RankedHand rankedHand = new RankedHand(p);
            System.out.println(p.getName() + " : score = " +  rankedHand.getRankedHandScore() +
                    " type Hand: " + rankedHand.getRankedHandType().getHandType());
            System.out.println();

            if (rankedHand.getRankedHandScore() >= bestHandValue) {
                bestHandValue = rankedHand.getRankedHandScore();
                winner = p;
            }
        }
        System.out.println("Before adding money: " + winner.getName() + " " + winner.getMoney());
        winner.addMoney(table.getTotalMoney());
        System.out.println("Winner is: " + winner.getName() + " " + winner.getMoney());
        resetHand();

    }

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

    public boolean isEndGame() {
        return isEndGame;
    }

    public boolean isShowDown() {
        return isShowDown && noOfActivePlayer == 0;
    }

    public void dealCardByStage() {
        if (isShowDown() && !isOver) {
            checkWinner();
            isStarted = true;
        } else if (isFlop()) {
            dealFlop();
            isFlop = false;
            isTurn = true;
        } else if (isTurn()) {
            dealTurn();
            isTurn = false;
            isRiver = true;
        } else if (isRiver()) {
            dealRiver();
            isRiver = false;
            isShowDown = true;
        } else if (isEndGame) {
            start();
        } else {

        }
    }

    public Table getTable() {
        return table;
    }

    public int getNoOfActivePlayer() {
        return noOfActivePlayer;
    }

    public class Dealer {

        /**
         * deal two cards to players
         */

        public void dealPreFlopCard() {
            System.out.println("PreFlop round");
            for(int i = 0; i < playerList.size(); i++) {
                playerList.get(i).addCard(deckOfCard.deal(2));
                System.out.println("Card of this player: " + playerList.get(i).getPlayerHands().toString());
            }
        }

        public void dealFlopCard() {
            System.out.println("Flop round");
            table.addCard(deckOfCard.deal(3));
        }

        public void dealTurnCard() {
            System.out.println("Turn round");
            table.addCard(deckOfCard.deal(1));
        }

        public void dealRiverCard() {
            System.out.println("River round");
            table.addCard(deckOfCard.deal(1));
        }
    }
}