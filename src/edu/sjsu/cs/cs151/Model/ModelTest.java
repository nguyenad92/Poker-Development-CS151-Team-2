package edu.sjsu.cs.cs151.Model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

/**
 * This class to test the Model of the game
 */

class ModelTest {
    public Model model = new Model();


    @Test
    public void startGameTest() {
        model.start();
        assertTrue(model.isStarted() == true);
        assertTrue(model.isOver() == false);
        assertTrue(model.getPlayerList().size() > 0);
    }

    @Test
    public void playerHandFlushTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);
        ArrayList<Card>  setOfCards = new ArrayList<>();

        //Check FLUSH
        setOfCards.add(new Card(1,0));
        setOfCards.add(new Card(2,0));
        setOfCards.add(new Card(3,0));
        setOfCards.add(new Card(4,0));
        setOfCards.add(new Card(5,0));
        setOfCards.add(new Card(4,1));
        setOfCards.add(new Card(10,0));

        model.getPlayerList().get(0).addCard(setOfCards);

        RankedHand check = new RankedHand(model.getPlayerList().get(0));

        System.out.println(check.getRankedHandType().getHandType());

        assertTrue(check.getRankedHandType() == RankedHandType.FLUSH);

    }

    @Test
    public void playerHandStraightTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("8s"));
        setOfCards.add(new Card("9h"));
        setOfCards.add(new Card("10s"));
        setOfCards.add(new Card("11c"));
        setOfCards.add(new Card("12c"));
        setOfCards.add(new Card("7h"));

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.STRAIGHT);
    }

    @Test
    public void playerHandOnePairTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("7h"));

        setOfCards.add(new Card("2s"));
        setOfCards.add(new Card("3h"));
        setOfCards.add(new Card("10s"));
        setOfCards.add(new Card("11c"));
        setOfCards.add(new Card("12c"));


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.ONE_PAIR);

    }

    @Test
    public void playerHandTwoPairTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("7s"));
        setOfCards.add(new Card("7h"));

        setOfCards.add(new Card("2s"));
        setOfCards.add(new Card("2h"));

        setOfCards.add(new Card("10s"));
        setOfCards.add(new Card("11c"));
        setOfCards.add(new Card("12c"));


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.TWO_PAIRS);

    }

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


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.THREE_OF_A_KIND);

    }

    @Test
    public void playerHandFullHouseTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  setOfCards = new ArrayList<>();

        setOfCards.add(new Card("As"));

        setOfCards.add(new Card("Qs"));
        setOfCards.add(new Card("Qh"));
        setOfCards.add(new Card("Qc"));

        setOfCards.add(new Card("Tc"));
        setOfCards.add(new Card("Td"));


        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.FULL_HOUSE);

    }

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

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.FOUR_OF_A_KIND);

    }

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

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.STRAIGHT_FLUSH);

    }

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

        RankedHand check = new RankedHand(model.getPlayerList().get(0));
        assertTrue(check.getRankedHandType() == RankedHandType.ROYAL_FLUSH);

    }
}