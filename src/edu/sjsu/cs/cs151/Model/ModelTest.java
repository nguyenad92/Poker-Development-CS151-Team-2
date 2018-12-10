package edu.sjsu.cs.cs151.Model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLOutput;
import java.util.ArrayList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class to test the Model of the game
 */

class ModelTest {
    public Model model = new Model();


    Model a = new Model();

    /**
     * Test if game starts correctly
     */
    @Test
    public void startGameTest() {
        model.start();
        assertTrue(model.isStarted() == true);
        assertTrue(model.isOver() == false);
        assertTrue(model.getPlayerList().size() > 0);
    }

    /**
     * Test if player has FLUSH
     */
    @Test
    public void playerHandFlushTest() {
        model.start();
        model.resetHand();
        model.setIsOver(false);
        ArrayList<Card> setOfCards = new ArrayList<>();

        //Check FLUSH
        setOfCards.add(new Card(1, 0));
        setOfCards.add(new Card(2, 0));
        setOfCards.add(new Card(3, 0));
        setOfCards.add(new Card(4, 0));
        setOfCards.add(new Card(9, 0));
        setOfCards.add(new Card(4, 1));
        setOfCards.add(new Card(10, 0));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));

        System.out.println(check.getRankedHandType().getHandType());

        assertTrue(check.getRankedHandType() == RankedHandType.FLUSH);
    }

    /**
     * Test if player has FULL HOUSE
     */
    @Test
    void checkFullHouse() {
        a.start();
        a.resetHand();

        ArrayList<Card> Currentcards = new ArrayList<>();
        Currentcards.add(new Card(0,0));
        Currentcards.add(new Card(1,0));
        Currentcards.add(new Card(2,1));
        Currentcards.add(new Card(3,1));
        Currentcards.add(new Card(2,0));
        Currentcards.add(new Card(12,3));
        Currentcards.add(new Card(1,0));

        a.getDealerPlayer().addCard(Currentcards);
//        a.dealPreFlop();


        ArrayList<Card> dealerCards = new ArrayList<>();
        dealerCards.add(new Card(12,0));
        dealerCards.add(new Card(11,0));
        dealerCards.add(new Card(10,0));
        dealerCards.add(new Card(9,0));
        dealerCards.add(new Card(8,0));
        dealerCards.add(new Card(10,1));
        dealerCards.add(new Card(1,1));

        a.getBigBlindPlayer().addCard(dealerCards);

        a.checkWinnerTest();

        RankedHand check1 = new RankedHand(a.getDealerPlayer());
        RankedHand check2 = new RankedHand(a.getBigBlindPlayer());
        assertEquals(check1.getRankedHandType(), RankedHandType.STRAIGHT);
        assertEquals(check2.getRankedHandType(), RankedHandType.FLUSH);
        assertEquals(a.getBigBlindPlayer(), a.getWinner());
        assertTrue(check1.getRankedHandType() == RankedHandType.STRAIGHT);
    }

    /**
     * Test if player has STRAIGHT
     */
    @Test
    public void playerHandStraightTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("8s"));
        setOfCards.add(new Card("9h"));
        setOfCards.add(new Card("Ts"));
        setOfCards.add(new Card("Jc"));
        setOfCards.add(new Card("2c"));
        setOfCards.add(new Card("7h"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertEquals(RankedHandType.STRAIGHT, check.getRankedHandType());
    }

    /**
     * Test if player has ONE PAIR
     */
    @Test
    public void playerHandOnePairTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("Ts"));
        setOfCards.add(new Card("7h"));

        setOfCards.add(new Card("2s"));
        setOfCards.add(new Card("3h"));
        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("6c"));
        setOfCards.add(new Card("5c"));

        System.out.println(setOfCards.get(0).getRank());
        System.out.println(setOfCards.get(0).getSuit());

        model.getPlayerList().get(0).addCard(setOfCards);


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertEquals(RankedHandType.ONE_PAIR, check.getRankedHandType());

    }

    /**
     * Test if player has TWO PAIRS
     */
    @Test
    public void playerHandTwoPairsTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("7h"));

        setOfCards.add(new Card("2s"));
        setOfCards.add(new Card("2h"));

        setOfCards.add(new Card("Ts"));
        setOfCards.add(new Card("Jc"));
        setOfCards.add(new Card("Qc"));


        model.getPlayerList().get(0).addCard(setOfCards);


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
//        assertTrue(check.getRankedHandType() == RankedHandType.TWO_PAIRS);
        assertEquals(RankedHandType.TWO_PAIRS, check.getRankedHandType() );

    }

    /**
     * Test if player has THREE OF A KIND
     */
    @Test
    public void playerThreeOfAKindTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("Ah"));

        setOfCards.add(new Card("Qs"));
        setOfCards.add(new Card("Qh"));
        setOfCards.add(new Card("Qc"));

        setOfCards.add(new Card("Th"));
        setOfCards.add(new Card("8s"));
        setOfCards.add(new Card("6c"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.THREE_OF_A_KIND);

    }

    /**
     * Test if player has FULL HOUSE
     */
    @Test
    public void playerHandFullHouseTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("Qs"));
        setOfCards.add(new Card("9s"));

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("Qh"));
        setOfCards.add(new Card("Qc"));

        setOfCards.add(new Card("Tc"));
        setOfCards.add(new Card("Td"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertEquals(RankedHandType.FULL_HOUSE, check.getRankedHandType());
//        assertTrue(check.getRankedHandType() == RankedHandType.FULL_HOUSE);

    }

    /**
     * Test if player has FOUR OF A KIND
     */
    @Test
    public void playerHandFourOfAKindTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("As"));
        setOfCards.add(new Card("Ah"));
        setOfCards.add(new Card("Ac"));
        setOfCards.add(new Card("Ad"));

        setOfCards.add(new Card("Td"));
        setOfCards.add(new Card("8c"));
        setOfCards.add(new Card("Qs"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.FOUR_OF_A_KIND);

    }

    /**
     * Test if player has STRAIGHT FLUSH
     */
    @Test
    public void playerHandStraightFlushTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("Ks"));
        setOfCards.add(new Card("Qs"));
        setOfCards.add(new Card("Js"));
        setOfCards.add(new Card("Ts"));
        setOfCards.add(new Card("9s"));

        setOfCards.add(new Card("8c"));
        setOfCards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertEquals(RankedHandType.STRAIGHT_FLUSH, check.getRankedHandType());

    }

    /**
     * Test if player has ROYAL FLUSH
     */
    @Test
    public void playerHandRoyalFlushTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("Ks"));
        setOfCards.add(new Card("Qs"));
        setOfCards.add(new Card("Js"));
        setOfCards.add(new Card("Ts"));
        setOfCards.add(new Card("As"));

        setOfCards.add(new Card("8c"));
        setOfCards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertEquals(RankedHandType.ROYAL_FLUSH,check.getRankedHandType() );

    }
}