package edu.sjsu.cs.cs151.Message;

public class ActionCallMessage extends Message {

    public ActionCallMessage(int callamount) {
        super("Call", callamount);

    }
}
