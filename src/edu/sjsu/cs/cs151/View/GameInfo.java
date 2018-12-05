package edu.sjsu.cs.cs151.View;

import java.util.ArrayList;

import edu.sjsu.cs.cs151.Model.*;

public class GameInfo {

    private boolean isStarted, isOver, isFlop, isTurn, isRiver;
    private int dealerPosition, currentActorPosition, bigBlind;
    private Player currentPlayer, dealerPlayer;
    private ArrayList<Player> playerList;
    private int currentBet, potTotal;
    
    public GameInfo(Model model) {
        this.isStarted = model.isStarted();
        this.isOver = model.isOver();
        this.dealerPosition = model.getDealerPosition();
        this.currentActorPosition = model.getCurrentActorPosition();
        this.currentPlayer = model.getCurrentActor();
        this.playerList = model.getActivePlayerList();
        currentBet = model.getTable().getCurrentBet();
        potTotal = model.getTable().getTotalMoney();
        dealerPlayer = model.getDealerPlayer();
//        this.currPot = model.getPot();
        if (isStarted) {
//            this.dealer= model.getDealer().iterator();
//            this.player = model.getPlayer().iterator();
        }
    }
    
    public ArrayList<Player> getPlayerList(){
    	return playerList;
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

    public boolean isStarted() {
        return isStarted;
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
}
