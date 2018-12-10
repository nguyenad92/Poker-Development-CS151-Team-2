package edu.sjsu.cs.cs151.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class to test the Model of the game
 */

class ModelTest {

    Model a = new Model();
    @Test
    void checkFullHouse() {
        a.start();
        a.resetHand();

//        ArrayList<Card> Currentcards = new ArrayList<>();
//        Currentcards.add(new Card(0,0));
//        Currentcards.add(new Card(1,0));
//        Currentcards.add(new Card(2,1));
//        Currentcards.add(new Card(8,1));
//        Currentcards.add(new Card(2,1));
//        Currentcards.add(new Card(12,3));
//        Currentcards.add(new Card(1,0));
//
//        a.getDealerPlayer().addCard(Currentcards);

        ArrayList<Card> dealerCards = new ArrayList<>();

        dealerCards.add(new Card(11,0));
        dealerCards.add(new Card(12,0));
        dealerCards.add(new Card(10,0));
        dealerCards.add(new Card(9,0));
        dealerCards.add(new Card(8,0));
        dealerCards.add(new Card(10,1));
        dealerCards.add(new Card(1,1));

        a.getBigBlindPlayer().addCard(dealerCards);

//        a.checkWinner();

//        RankedHand check1 = new RankedHand(a.getDealerPlayer());
        RankedHand check2 = new RankedHand(a.getBigBlindPlayer());
//        assertEquals(check1.getRankedHandType(), RankedHandType.STRAIGHT);
        assertEquals(check2.getRankedHandType(), RankedHandType.STRAIGHT);
//        assertEquals(a.getBigBlindPlayer(), a.getWinner());

    }

    @Test
    void resetHand() {
    }

    @Test
    void showDown() {
    }

    @Test
    void nextPlayerToAct() {
    }

    @Test
    void betting() {
    }

    @Test
    void getAllowedAction() {
    }
}