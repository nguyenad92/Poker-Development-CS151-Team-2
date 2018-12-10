package edu.sjsu.cs.cs151.Message;

/**
 * Action Call Message with Callamount
 */
public class ActionCallMessage extends Message {

    public ActionCallMessage(int callamount) {
        super("Call", callamount);

    }
}
