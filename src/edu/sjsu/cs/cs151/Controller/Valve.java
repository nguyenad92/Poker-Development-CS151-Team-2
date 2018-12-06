package edu.sjsu.cs.cs151.Controller;

import edu.sjsu.cs.cs151.Message.Message;

/**
 * An Interface that store the Reponse of an action
 */
public interface Valve {

    /**
     *
     * @param message
     * @return
     */
    ValveResponse execute(Message message);
}
