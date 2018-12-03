package edu.sjsu.cs.cs151.Controller;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.View;
import sun.jvm.hotspot.opto.Block;
import sun.plugin2.message.Message;

import java.util.*;
import java.util.concurrent.*;

public class Controller {
    private BlockingQueue<Message> queue;
    private View view;
    private Model model;
    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(BlockingQueue<Message> queue) {
        this.view = new View(this);
        this.model = new Model();
        this.queue = queue;
    }

    public void mainLoop() throws Exception {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message= null;

        while (response != ValveResponse.FINISH) {
            try {
                message = (Message) queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Valve valve : valves) {
            response = valve.execute(message);
            if (response != ValveResponse.MISS) break;
        }
    }
}
