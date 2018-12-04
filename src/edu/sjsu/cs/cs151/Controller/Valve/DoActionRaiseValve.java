package edu.sjsu.cs.cs151.Controller.Valve;

import edu.sjsu.cs.cs151.Controller.*;
import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;
import java.util.concurrent.BlockingQueue;

public class DoActionRaiseValve extends Controller implements Valve {

    DoActionRaiseValve(View view, Model model, BlockingQueue<Message> queue) {
        super(view, model, queue);
    }


    public ValveResponse execute(Model model, View view, Message message) {
        //if(!(message instanceof NewGameMessage))
        if (message.getClass() != NewGameMessage.class) {
            return ValveResponse.MISS;
        }


        model.raise(message.getAmount());
        model.nextPlayerToAct();
        view.change(updateGameInfo());
        view.setControlPannel("RAISE");

        return ValveResponse.EXECUTED;
    }
}