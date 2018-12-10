package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Model.Card;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.Model.Player;

import java.util.ArrayList;

/**
 * Class that stores all information of the game
 */
public class GameInfo {

    private boolean hasWinner;
    private int bigBlind;
    private Player currentPlayer, winner;
    private ArrayList<Player> playerList;
    private int currentBet, potTotal;
    private ArrayList<Card> communityCards;

    /**
     * Constructor of the class
     * @param model
     */
    public GameInfo(Model model) {
        currentPlayer = model.getCurrentActor();
        playerList = model.getActivePlayerList();
        currentBet = model.getTable().getCurrentBet();
        potTotal = model.getTable().getTotalMoney();
        winner = model.getWinner();

        hasWinner = model.isHasWinner();
        communityCards = model.getTable().getCommunityCards();
        bigBlind = model.getBigBlind();
    }

    /**
     * Information of list of players
     * @return
     */
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Information of CURRENT PLAYER
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Information of BIG BLIND of player
     * @return
     */
    public int getBigBlind() {
        return bigBlind;
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

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Player getWinner() {
        return winner;
    }
}