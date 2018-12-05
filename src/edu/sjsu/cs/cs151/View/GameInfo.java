package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Model.*;

import java.util.ArrayList;

public class GameInfo {

    private boolean isStarted, isOver, isFlop, isTurn, isRiver;
    private int dealerPosition, currentActorPosition, bigBlind;
    private Player currentPlayer, dealerPlayer;
    private int currentBet, potTotal;
    private ArrayList<Card> communityCards = new ArrayList<>();
    
    public GameInfo(Model model) {
        isStarted = model.isStarted();
        isOver = model.isOver();
        dealerPosition = model.getDealerPosition();
        currentActorPosition = model.getCurrentActorPosition();
        currentPlayer = model.getCurrentActor();
        currentBet = model.getTable().getCurrentBet();
        potTotal = model.getTable().getTotalMoney();
        dealerPlayer = model.getDealerPlayer();
        communityCards = model.getTable().getCommunityCards();

//        this.currPot = model.getPot();
        if (isStarted) {
//            this.dealer= model.getDealer().iterator();
//            this.player = model.getPlayer().iterator();
        }
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
