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
    public void endGameTest(){
        model.start();
        model.dealPreFlop();
        assertTrue(model.isFlop() == true);

    }

}