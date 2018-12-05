package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.ActionCheckMessage;
import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Message.NewGameMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

public class ControlPanel extends JPanel {

    BlockingQueue<Message> messageQueue;

    /** The Raise button. */
    private final JButton newGameButton;

    /** The Check button. */
    private final JButton checkButton;

    /** The Call button. */
    private final JButton callButton;

    /** The Bet button. */
    private final JButton betButton;

    /** The Raise button. */
    private final JButton raiseButton;

    /** The Fold button. */
    private final JButton foldButton;

    public ControlPanel(BlockingQueue<Message> queue) {
        messageQueue = queue;
        newGameButton = createActionButton("NEW GAME");
        checkButton = createActionButton("CHECK");
        callButton = createActionButton("CALL");
        betButton = createActionButton("BET");
        raiseButton = createActionButton("RAISE");
        foldButton = createActionButton("FOLD");
        this.add(newGameButton);
    }

    public void setControlPanel(String actionName) {

    }

    private JButton createActionButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setMnemonic(buttonName.charAt(0));
        button.setSize(100, 30);
        button.addActionListener(new newGameActionListener());
        return button;
    }

    public class newGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new NewGameMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

}