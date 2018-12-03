package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;

public class Dealer {

    private Table table;
    private DeckOfCard deck;
    private ArrayList<Player> playerList;

    public Dealer(Table table, DeckOfCard deck, ArrayList<Player> playerList) {
        this.table = table;
        this.deck = deck;
        this.playerList = playerList;
    }

    /**
     * deal two cards to players
     */

    public void dealPreFlopCard() {
        for(int i = 0; i < playerList.size(); i++) {
            playerList.get(i).addCard(deck.deal(2));
        }

    }

    public void dealFlopCard() {
        for(int i = 0; i < 3; i++)
            table.addCard(deck.deal());
    }

    public void dealTurnCard() {
        table.addCard(deck.deal());
    }

    public void dealRiverCard() {
        table.addCard(deck.deal());
    }
}
