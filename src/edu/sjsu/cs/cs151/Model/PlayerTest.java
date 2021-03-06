package edu.sjsu.cs.cs151.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class PlayerTest {

    private Player player = new Player("ADN", 5000   );;

    @Test
    void payMoney() {
        player.payMoney(1000);
        assertEquals(4000, player.getMoney());
        assertEquals(1000, player.getCurrentBet());

    }

    @Test
    void getName() {
        String nameExpected = "ADN";
        String nameActual = player.getName();
        assertEquals(nameExpected, nameActual);
        assertEquals("ADN", player.getName());
    }

    @Test
    void getCurrentBet() {
        player.setCurrentBet(30);
        assertEquals(30, player.getCurrentBet());
    }

    @Test
    void resetHand() {
        player.resetHand();
        assertEquals(0, player.getCurrentBet());
    }

    @Test
    void addMoney() {
        player.addMoney(3000);
        assertEquals(8000, player.getMoney());
    }


    @Test
    void getCurrentAction() {
        player.setCurrentAction("raise");
        assertEquals("raise", player.getCurrentAction());
    }

    @Test
    void getPlayerHands() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card("8s");
        Card card2 = new Card(9, 4);
        cards.add(card1);
        cards.add(card2);
        player.addCard(cards);
        assertEquals(card1, player.getCard().get(0));
    }

}

