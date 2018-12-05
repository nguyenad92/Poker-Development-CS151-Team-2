package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;


<<<<<<< HEAD
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

    /**
     * Flop stage of the game, deal 3 cards
     */
    public void dealFlopCard() {
        for(int i = 0; i < 3; i++)
            table.addCard(deck.deal());
    }

    //Deal the 4th card
    public void dealTurnCard() {
        table.addCard(deck.deal());
    }

    //Deal last card
    public void dealRiverCard() {
        table.addCard(deck.deal());
    }
}
=======
>>>>>>> branch 'master' of https://github.com/NivlaCuong/Poker-Development-CS151-Team-2.git
