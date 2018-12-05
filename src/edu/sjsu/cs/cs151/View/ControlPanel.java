package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.*;

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

        addButton();
    }

    public void setControlPanel(String actionName) {
        if (actionName.equals("CHECK")) {
            // Add Check, Bet, Fold
        } else if (actionName.equals("CALL")) {
            // Add Fold, Call, Raise
        } else if (actionName.equals("BET") || actionName.equals("RAISE")) {
            // Add Fold, Call, Raise
        } else if (actionName.equals("FOLD")) {
            // Add Check, Bet, Fold
        }
    }

    private void addButton() {
        add(newGameButton);
        add(checkButton);
        add(callButton);
        add(betButton);
        add(raiseButton);
        add(foldButton);
    }

    private JButton createActionButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setMnemonic(buttonName.charAt(0));
        button.setSize(100, 30);
        if (buttonName.equals("NEW GAME")) button.addActionListener(new newGameActionListener());
        if (buttonName.equals("CHECK")) button.addActionListener(new checkActionListener());
        if (buttonName.equals("CALL")) button.addActionListener(new callActionListener());
        if (buttonName.equals("RAISE")) button.addActionListener(new raiseActionListener());
        if (buttonName.equals("FOLD")) button.addActionListener(new foldActionListener());
        if (buttonName.equals("BET")) button.addActionListener(new betActionListener());
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

    public class checkActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionCheckMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class callActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionCallMessage(1000));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class betActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionBetMessage(1000));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class raiseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionRaiseMessage(1500));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class foldActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionFoldMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

}