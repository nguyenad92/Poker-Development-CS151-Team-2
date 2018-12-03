package edu.sjsu.cs.cs151.Controller;

import sun.plugin2.message.Message;

public abstract class DoNewGameValve implements Valve {
    public ValveResponse execute(Message message){
//        if(message.getClass() != NewGameMessage.class){
//            return ValveResponse.MISS;
//        }
        //actions in Model
        // actions in View
        return ValveResponse.EXECUTED;
        // }
    }
}
