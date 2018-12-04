package edu.sjsu.cs.cs151.Message;

public class Message {

    /** The action's name. */
    private final String name;

    /** The amount (if appropriate). */
    private final int amount;

    public Message(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
