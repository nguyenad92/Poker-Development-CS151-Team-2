package edu.sjsu.cs.cs151.Model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RankedHandTest {

    Model model = new Model();
    @Test
    public void compareOnePairTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("7s"));
        player0Cards.add(new Card("7h"));

        player0Cards.add(new Card("2s"));
        player0Cards.add(new Card("3h"));
        player0Cards.add(new Card("Ts"));
        player0Cards.add(new Card("Jc"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("8s"));
        player1Cards.add(new Card("8h"));

        player1Cards.add(new Card("2s"));
        player1Cards.add(new Card("3h"));
        player1Cards.add(new Card("Ts"));
        player1Cards.add(new Card("Jc"));
        player1Cards.add(new Card("Qc"));

        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }

    @Test
    public void compareTwoPairsTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("7s"));
        player0Cards.add(new Card("7h"));

        player0Cards.add(new Card("2s"));
        player0Cards.add(new Card("2h"));
        player0Cards.add(new Card("Ts"));
        player0Cards.add(new Card("Jc"));
        player0Cards.add(new Card("Qc"));

        //Player 0 has two pairs
        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("8s"));
        player1Cards.add(new Card("8h"));

        player1Cards.add(new Card("2s"));
        player1Cards.add(new Card("2h"));
        player1Cards.add(new Card("Ts"));
        player1Cards.add(new Card("Jc"));
        player1Cards.add(new Card("Qc"));

        //Player 1 has one pair
        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }

    @Test
    public void compareStraightTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("3s"));
        player0Cards.add(new Card("4h"));

        player0Cards.add(new Card("5s"));
        player0Cards.add(new Card("6h"));
        player0Cards.add(new Card("7s"));
        player0Cards.add(new Card("Jc"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("4s"));
        player1Cards.add(new Card("5h"));

        player1Cards.add(new Card("6s"));
        player1Cards.add(new Card("7h"));
        player1Cards.add(new Card("8s"));
        player1Cards.add(new Card("Jc"));
        player1Cards.add(new Card("Qc"));

        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }

    @Test
    public void compareFlushTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("3s"));
        player0Cards.add(new Card("Ts"));

        player0Cards.add(new Card("5s"));
        player0Cards.add(new Card("6h"));
        player0Cards.add(new Card("5d"));
        player0Cards.add(new Card("Js"));
        player0Cards.add(new Card("Qs"));

        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("4s"));
        player1Cards.add(new Card("Ks"));

        player0Cards.add(new Card("5s"));
        player0Cards.add(new Card("6h"));
        player0Cards.add(new Card("5d"));
        player0Cards.add(new Card("Js"));
        player0Cards.add(new Card("Qs"));

        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }

    @Test
    public void compareThreeOfKindTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("3s"));
        player0Cards.add(new Card("3h"));

        player0Cards.add(new Card("3c"));
        player0Cards.add(new Card("6h"));
        player0Cards.add(new Card("7s"));
        player0Cards.add(new Card("Jc"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("4s"));
        player1Cards.add(new Card("4h"));

        player0Cards.add(new Card("3s"));
        player0Cards.add(new Card("4c"));
        player0Cards.add(new Card("7s"));
        player0Cards.add(new Card("Jc"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }

    @Test
    public void compareFourOfKindTest(){
        model.start();
        model.resetHand();
        model.setIsOver(false);

        ArrayList<Card>  player0Cards = new ArrayList<>();
        player0Cards.add(new Card("6s"));
        player0Cards.add(new Card("6h"));

        player0Cards.add(new Card("6d"));
        player0Cards.add(new Card("6c"));
        player0Cards.add(new Card("7c"));
        player0Cards.add(new Card("7d"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(0).addCard(player0Cards);

        ArrayList<Card>  player1Cards = new ArrayList<>();
        player1Cards.add(new Card("7s"));
        player1Cards.add(new Card("7h"));

        player0Cards.add(new Card("6d"));
        player0Cards.add(new Card("6c"));
        player0Cards.add(new Card("7c"));
        player0Cards.add(new Card("7d"));
        player0Cards.add(new Card("Qc"));

        model.getPlayerList().get(1).addCard(player1Cards);

        model.checkWinnerTest();

        assertEquals(model.getPlayerList().get(1), model.getWinner());
    }


}