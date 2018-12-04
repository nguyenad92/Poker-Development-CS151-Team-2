package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Model.Model;

public class GameInfo {

    private boolean isStarted, isOver, isFlop, isTurn, isRiver;
    private int dealerPosition, currentActorPosition, bigBlind;

    
    public GameInfo(Model model) {
        this.isStarted = model.isStarted();
        this.isOver = model.isOver();
        this.dealerPosition = model.getDealerPosition();
        this.currentActorPosition = model.getCurrentActorPosition();
//        this.currPot = model.getPot();
        if (isStarted) {
//            this.dealer= model.getDealer().iterator();
//            this.player = model.getPlayer().iterator();
        }
    }
}
