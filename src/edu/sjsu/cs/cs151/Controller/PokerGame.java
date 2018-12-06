package edu.sjsu.cs.cs151.Controller;

import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Main Poker Game Class to run the program with same Model, View, and Controller
 */
public class PokerGame {
    /**
     * MessageQueue: a communication between Controller and View
     */
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    /**
     * GUI of the app
     */
    private static View view;

    /**
     * Model of the app
     */
    private static Model model;

    /**
     * Main function to run the program
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception {
        view = View.init(queue);
        model = new Model();
        Controller game = new Controller(view, model, queue);
        game.mainLoop();
        view.dispose();
        queue.clear();
    }
}
