package edu.sjsu.cs.cs151.Message;

public class ActionRaiseMessage extends Message {

    public ActionRaiseMessage(int amount) {
        super("Deal", amount);
    }
}
