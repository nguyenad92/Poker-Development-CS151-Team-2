package edu.sjsu.cs.cs151.Controller;

import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.View;
import sun.plugin2.message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PokerGame {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
    private static View view;
    private static Model model;

    public static void main(String [] args) throws Exception {
        Controller game = new Controller(queue);
//        Controller game = new Controller(view, model, queue);
        game.mainLoop();
        view.dispose();
        queue.clear();
    }
}
