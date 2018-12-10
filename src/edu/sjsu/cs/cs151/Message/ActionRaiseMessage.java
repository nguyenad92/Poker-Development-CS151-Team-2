package edu.sjsu.cs.cs151.Message;

/**
 *
 */
public class ActionRaiseMessage extends Message {

    /**
     *
     * @param amount
     */
    public ActionRaiseMessage(int amount) {
        super("Raise", amount);
    }
}
