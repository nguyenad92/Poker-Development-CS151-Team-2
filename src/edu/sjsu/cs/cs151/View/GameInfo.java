package edu.sjsu.cs.cs151.View;

import java.util.ArrayList;
import edu.sjsu.cs.cs151.Model.*;

public class GameInfo {

    private boolean isStarted, isOver, isFlop, isTurn, isRiver, isEndGame;
    private int dealerPosition, currentActorPosition, bigBlind;
    private Player currentPlayer, dealerPlayer, bigBlindPlayer;
    private ArrayList<Player> playerList;
    private int currentBet, potTotal, currentPlayerBet;
    private ArrayList<Card> communityCards;
    private String currentPlayerAction;
    
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

        communityCards = model.getTable().getCommunityCards();
        bigBlind = model.getBigBlind();
        currentPlayerAction = model.getCurrentActor().getCurrentAction();
        currentPlayerBet = model.getCurrentActor().getCurrentBet();
    }

    public Player getBigBlindPlayer() {
        return bigBlindPlayer;
    }

    public String getCurrentPlayerAction() {
        return currentPlayerAction;
    }

    public ArrayList<Player> getPlayerList(){
    	return playerList;
    }

    public int getCurrentPlayerBet() {
        return currentPlayerBet;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getDealerPlayer() {
        return dealerPlayer;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public int getDealerPosition() {
        return dealerPosition;
    }

    public int getCurrentActorPosition() {
        return currentActorPosition;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getPotTotal() {
        return potTotal;
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    public boolean isOver() {
        return isOver;
    }
}
