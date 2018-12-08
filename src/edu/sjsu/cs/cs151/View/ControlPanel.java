package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

public class ControlPanel extends JPanel {

    BlockingQueue<Message> messageQueue;

    /** The Raise button. */
    private JButton newGameButton;

    /** The Check button. */
    private JButton checkButton;

    /** The Call button. */
    private JButton callButton;

    /** The Bet button. */
    private JButton betButton;

    /** The Raise button. */
    private JButton raiseButton;

    private JButton newHandButton;

    /** The Fold button. */
    private JButton foldButton;

    private JTextField betAmount;

    private GameInfo gameInfo;

    /**
     * The panel helps receiving action when user click on buttons
     * @param queue
     */
    public ControlPanel(BlockingQueue<Message> queue) {
        messageQueue = queue;
        newGameButton = createActionButton("NEW GAME");
        checkButton = createActionButton("CHECK");
        callButton = createActionButton("CALL");
        betButton = createActionButton("BET");
        raiseButton = createActionButton("RAISE");
        foldButton = createActionButton("FOLD");
        newHandButton = createActionButton("NEW HAND");
        betAmount = new JTextField(15);

        add(newGameButton);
    }

    /**
     * Set up the buttons when new game starts
     * @param gameInfo
     * @param actionName
     */
    public void setControlPanel(GameInfo gameInfo, String actionName) {
        this.gameInfo = gameInfo;
        clearAllButton();
        add(newGameButton);

        if (actionName.equals("NEW_GAME") || (actionName.equals("FOLD"))) {
            add(callButton);
            add(raiseButton);

        } else if (actionName.equals("CHECK")) {
            add(checkButton);
            add(betButton);
        } else if(actionName.equals("CALL")) {
            add(checkButton);
            add(raiseButton);
        } else if (actionName.equals("BET") || actionName.equals("RAISE") || actionName.equals("ALL_IN")) {
            add(foldButton);
            add(callButton);
            add(raiseButton);
        }
        switch (actionName) {
            case "NEW_HAND":
                add(newHandButton);
                break;
            case "NEW_GAME":
            case "FOLD":
                add(callButton);
                add(betButton);
                betAmount.setText(Integer.toString(gameInfo.getBigBlind()));
                add(betAmount);
                break;
            case "CHECK":
                add(checkButton);
                add(betButton);
                betAmount.setText(Integer.toString(gameInfo.getBigBlind()));
                add(betAmount);
                break;
            case "CALL":
                add(checkButton);
                add(raiseButton);
                betAmount.setText(Integer.toString(gameInfo.getBigBlind()));
                add(betAmount);
                break;
            case "BET":
            case "RAISE":
                add(foldButton);
                add(callButton);
                add(raiseButton);
                betAmount.setText(Integer.toString(gameInfo.getBigBlind()));
                add(betAmount);
                break;
        }

    }

    /**
     * Reset all actions
     */
    private void clearAllButton() {
        remove(newGameButton);
        remove(checkButton);
        remove(betButton);
        remove(raiseButton);
        remove(callButton);
        remove(foldButton);
        remove(newHandButton);
        remove(betAmount);
    }

    /**
     * Conduct the action when receiving from user
     * @param buttonName
     * @return
     */
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
        if (buttonName.equals("NEW HAND")) button.addActionListener(new newHandActionListener());
        return button;
    }

    /**
     * Class that do the NEW GAME action
     */
    public class newGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new NewGameMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the ALL IN action
     */
    public class newHandActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new newHandActionMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the CHECK action
     */
    public class checkActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionCheckMessage());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the CALL action
     */
    public class callActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionCallMessage(gameInfo.getCurrentBet()));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the BET action
     */
    public class betActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionBetMessage(Integer.parseInt(betAmount.getText())));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the RAISE action
     */
    public class raiseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionRaiseMessage(Integer.parseInt(betAmount.getText())));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Class that do the FOLD action
     */
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