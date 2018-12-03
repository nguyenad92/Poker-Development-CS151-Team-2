package edu.sjsu.cs.cs151.View;

import sun.plugin2.message.Message;

import java.util.concurrent.BlockingQueue;

public class View {

    public View init(BlockingQueue<Message> queue) {
        return new View();
    }

    public void dispose() {

    }
}
