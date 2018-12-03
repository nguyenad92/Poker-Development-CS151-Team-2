package edu.sjsu.cs.cs151.Controller;

public abstract class newGameValve implements Valve {
    public ValveResponse execute(String message){

        if(message.isEmpty()){
            return ValveResponse.MISS;
        }

        //actions in Model

        // actions in View


        return ValveResponse.EXECUTED;
    }
}
