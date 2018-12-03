package edu.sjsu.cs.cs151.Controller;

import sun.plugin2.message.Message;

public interface Valve {

    public ValveResponse execute(Message message);
}
