package edu.sjsu.cs.cs151.Message;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {


    @Test
    public void testBet() {
        ActionBetMessage betMes = new ActionBetMessage(300);
        assertEquals(300, betMes.getAmount());
        assertEquals("Bet", betMes.getName());
    }

    @Test
    public void testCall() {
        ActionCallMessage callMess = new ActionCallMessage(400);
        assertEquals(400, callMess.getAmount());
        assertEquals("Call", callMess.getName());
    }

    @Test
    public void testCheck() {
        ActionCheckMessage checkMess = new ActionCheckMessage();
        assertEquals(0, checkMess.getAmount());
        assertEquals("Check", checkMess.getName());
    }

    @Test
    public void testFold() {
        ActionFoldMessage foldMess = new ActionFoldMessage();
        assertEquals(0, foldMess.getAmount());
        assertEquals("Fold", foldMess.getName());
    }

    @Test
    public void testRaise() {
        ActionRaiseMessage raiseMess = new ActionRaiseMessage(700);
        assertEquals(700, raiseMess.getAmount());
        assertEquals("Raise", raiseMess.getName());
    }

    @Test
    public void testAllIn() {
        AllInactionMessage allInMess = new AllInactionMessage(1000);
        assertEquals(1000, allInMess.getAmount());
        assertEquals("All In", allInMess.getName());
    }

    @Test
    public void dealCardMess() {
        DealCardMessage dealCardMess = new DealCardMessage();
        assertEquals(0, dealCardMess.getAmount());
        assertEquals("Deal", dealCardMess.getName());
    }

    @Test
    public void testNewGame() {
        NewGameMessage newGameMess = new NewGameMessage();
        assertEquals(0, newGameMess.getAmount());
        assertEquals("NewGame", newGameMess.getName());
    }

    @Test
    public void testNewHand() {
        newHandActionMessage newHandMess = new newHandActionMessage();
        assertEquals(0, newHandMess.getAmount());
        assertEquals("NEW_HAND", newHandMess.getName());
    }
}