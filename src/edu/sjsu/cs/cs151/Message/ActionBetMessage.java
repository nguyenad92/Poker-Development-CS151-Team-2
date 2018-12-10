package edu.sjsu.cs.cs151.Message;

/**
 * Action Bet Message
 */
public class ActionBetMessage extends Message {

    /**
     *
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
