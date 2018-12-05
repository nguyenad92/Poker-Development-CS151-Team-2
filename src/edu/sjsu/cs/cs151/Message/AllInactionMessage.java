package edu.sjsu.cs.cs151.Message;

public class AllInactionMessage extends Message {

    /**
     *
     * @param betAmount
     */
    public AllInactionMessage(int betAmount) {
        super("All In", betAmount);
    }

    @Override
    public int getAmount() {
        return super.getAmount();
    }
}
