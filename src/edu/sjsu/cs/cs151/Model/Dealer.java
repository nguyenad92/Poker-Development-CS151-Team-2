package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

public class Dealer {

    private Table table;
    private ArrayList<Player> playerList;
    private DeckOfCard deckOfCard;

    /**
     * define dealer
     * @param table given
     * @param deckOfCard given
     * @param playerList given
     */
    public Dealer(Table table, DeckOfCard deckOfCard, ArrayList<Player> playerList) {
        this.table = table;
        this.playerList = playerList;
        this.deckOfCard = deckOfCard;
    }

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

    /**
     * deal 3 community cards on the table.
     */
    public void dealFlopCard() {
        table.addCard(deckOfCard.deal(3));
    }

    /**
     * Deal 1 community card on the table.
     */
    public void dealTurnCard() {
        table.addCard(deckOfCard.deal(1));
    }

    /**
     * deal the last community card on the table.
     */
    public void dealRiverCard() {
        table.addCard(deckOfCard.deal(1));
    }
}
