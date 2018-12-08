package edu.sjsu.cs.cs151.View;

import java.util.ArrayList;
import edu.sjsu.cs.cs151.Model.*;

/**
 * Class that stores all information of the game
 */
public class GameInfo {

    private boolean isStarted, isOver, isFlop, isTurn, isRiver, isEndGame, hasWinner;
    private int dealerPosition, currentActorPosition, bigBlind;
    private Player currentPlayer, dealerPlayer, bigBlindPlayer, winner;
    private ArrayList<Player> playerList;
    private int currentBet, potTotal, currentPlayerBet;
    private ArrayList<Card> communityCards;
    private String currentPlayerAction;

    /**
     * Constructor of the class
     * @param model
     */
    public GameInfo(Model model) {
        isStarted = model.isStarted();
        isOver = model.isOver();
        dealerPosition = model.getDealerPosition();
        dealerPlayer = model.getDealerPlayer();
        bigBlindPlayer = model.getBigBlindPlayer();
        currentActorPosition = model.getCurrentActorPosition();
        currentPlayer = model.getCurrentActor();
        playerList = model.getActivePlayerList();
        currentBet = model.getTable().getCurrentBet();
        potTotal = model.getTable().getTotalMoney();
        winner = model.getWinner();

        hasWinner = model.isHasWinner();
        communityCards = model.getTable().getCommunityCards();
        bigBlind = model.getBigBlind();
        currentPlayerAction = model.getCurrentActor().getCurrentAction();
        currentPlayerBet = model.getCurrentActor().getCurrentBet();
    }

    /**
     * Information of player who doing BIG BLIND
     * @return
     */
    public Player getBigBlindPlayer() {
        return bigBlindPlayer;
    }

    /**
     * ACTION of player
     * @return
     */
    public String getCurrentPlayerAction() {
        return currentPlayerAction;
    }

    /**
     * Information of list of players
     * @return
     */
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Information of PLAYER BET
     * @return
     */
    public int getCurrentPlayerBet() {
        return currentPlayerBet;
    }

    /**
     * Information of CURRENT PLAYER
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Information of DEALER
     * @return
     */
    public Player getDealerPlayer() {
        return dealerPlayer;
    }

    /**
     * Information of BIG BLIND of player
     * @return
     */
    public int getBigBlind() {
        return bigBlind;
    }

    /**
     * Information of POSITION of player
     * @return
     */
    public int getDealerPosition() {
        return dealerPosition;
    }

    /**
     * Information of ACTOR position
     * @return
     */
    public int getCurrentActorPosition() {
        return currentActorPosition;
    }

    /**
     * Information of BET
     * @return
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Information of POT
     * @return
     */
    public int getPotTotal() {
        return potTotal;
    }

    /**
     * Information of COMMUNITY CARDS
     * @return
     */
    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     * Information if game is OVER
     * @return
     */
    public boolean isOver() {
        return isOver;
    }

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Player getWinner() {
        return winner;
    }
}