package edu.sjsu.cs.cs151.Message;

/**
 * Raise message
 */
public class ActionRaiseMessage extends Message {

    /**
     * Inheritance of Message
     * @param amount
     */
    public ActionRaiseMessage(int amount) {
        super("Raise", amount);
    }
}
