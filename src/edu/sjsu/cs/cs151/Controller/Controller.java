package edu.sjsu.cs.cs151.Controller;
import edu.sjsu.cs.cs151.Controller.Valve.*;
import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;

import java.util.*;
import java.util.concurrent.*;

public class Controller {
    private BlockingQueue<Message> queue;
    private View view;
    private Model model;
    private List<Valve> valves = new LinkedList<>();
    private GameInfo gameInfo;

    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        addAllValves();
    }

    public void mainLoop() throws Exception {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;

        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Valve valve : valves) {
            response = valve.execute(model, view, message);
            if (response != ValveResponse.MISS) break;
        }
    }


    public GameInfo updateGameInfo() {
        gameInfo = new GameInfo(model);
        return gameInfo;
    }
    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public List<Valve> getValves() {
        return valves;
    }

    public void addAllValves() {
        valves.add(new StartNewGameValve());
        valves.add(new DoActionCheckValve());
        valves.add(new DoActionCallValve());
        valves.add(new DoActionBetValve());
        valves.add(new DoActionRaiseValve());
        valves.add(new DoActionFoldValve());
        valves.add(new DoDealCardValve());

    }
}
