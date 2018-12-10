package edu.sjsu.cs.cs151.Message;

public class ActionBetMessage extends Message {

    /**
     * Inheritance of Message
     * @param betAmount
     */
    public ActionBetMessage(int betAmount) {
        super("Bet", betAmount);
    }

    @Override
    public int getAmount() {
        return super.getAmount();
    }
}
