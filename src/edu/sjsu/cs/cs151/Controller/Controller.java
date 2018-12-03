package edu.sjsu.cs.cs151.Controller;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.View;

import java.util.*;
import java.util.concurrent.*;

public class Controller {
    private BlockingQueue<String> queue;
    private View view;
    private Model model;
    private List<newGameValve> valves = new LinkedList<>();
    private static boolean gameInfo;

    public Controller(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void mainLoop() throws Exception {
        this.view = new View(this, queue);
        this.model = new Model();
        ValveResponse response = ValveResponse.EXECUTED;
        String message;

        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take();
                for (Valve valve : valves) {
                    response = valve.execute(message);
                    if (response != ValveResponse.MISS) break;
                    else {

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        model.start();
        gameInfo = model.isStarted();
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public BlockingQueue<String> getQueue() {
        return queue;
    }

    public List<newGameValve> getValves() {
        return valves;
    }

    public static boolean getGameInfo() {
        return gameInfo;
    }
}
