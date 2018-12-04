package edu.sjsu.cs.cs151.Controller.Valve;

import edu.sjsu.cs.cs151.Controller.*;
import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;
import java.util.concurrent.BlockingQueue;

public class DealFlopCardValve extends Controller implements Valve {

    DealFlopCardValve(View view, Model model, BlockingQueue<Message> queue) {
        super(view, model, queue);
    }

    public ValveResponse execute(Model model, View view, Message message) {
        if (message.getClass() != NewGameMessage.class) {
            return ValveResponse.MISS;
        }

        view.change(updateGameInfo());
        view.setPlayerPannel("FLOP");
        return ValveResponse.EXECUTED;
    }
}
