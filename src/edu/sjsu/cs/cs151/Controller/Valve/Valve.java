package edu.sjsu.cs.cs151.Controller.Valve;


import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;

public interface Valve {

    ValveResponse execute(Model model, View view, Message message);
}
