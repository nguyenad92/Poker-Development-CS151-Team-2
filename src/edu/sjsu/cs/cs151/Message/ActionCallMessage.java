package edu.sjsu.cs.cs151.Message;

/**
 * Action Call Message with Callamount
 */
public class ActionCallMessage extends Message {

    /**
     * Inheritance of Message
     * @param callamount
     */
    public ActionCallMessage(int callamount) {
        super("Call", callamount);

    }
}
