package edu.sjsu.cs.cs151.Controller;


import edu.sjsu.cs.cs151.Message.Message;

public interface Valve {

    ValveResponse execute(Message message);
}
