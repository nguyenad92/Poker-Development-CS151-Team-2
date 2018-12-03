package edu.sjsu.cs.cs151.Controller;

import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.View;
import sun.plugin2.message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PokerGame {
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static Model model;

    public static void main(String [] args) throws Exception {
        Controller game = new Controller(queue);
        game.mainLoop();
        view = game.getView();
        model = game.getModel();
        view.dispose();
        queue.clear();
    }
}
