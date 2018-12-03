package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Model.Player;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

/**
 * Main Frame of the Game
 */
public class View extends JFrame {

    private TablePanel basePanel;
    private BlockingQueue<String> queue;

    public View(Controller baseController, BlockingQueue<String> queue) {
        basePanel = new TablePanel(baseController);
        this.queue = queue;
        init();
    }

    public void dispose() {

    }

    /**
     *  Setup Frame for the Game
     */
    public void init() {
        this.setContentPane(basePanel);
        this.setSize(500, 500);
        this.setVisible(true);

//        this.add(playerPanel);

        JButton buttonNewGame = new JButton("NewGame");
        buttonNewGame.addActionListener(new NewGameListener());
        this.add(buttonNewGame);
    }

    public void change() {

    }

    public void addMessage() {

    }

    public class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                queue.put("START");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
