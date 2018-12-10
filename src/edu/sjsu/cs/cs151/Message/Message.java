package edu.sjsu.cs.cs151.Message;

/**
 * Parent class that keeps message of stages of game
 */
public class Message {

    /** The action's name. */
    private final String name;

    /** The amount (if appropriate). */
    private final int amount;

    /**
     *
     * @param name
     * @param amount
     */
    public Message(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }


    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

}
