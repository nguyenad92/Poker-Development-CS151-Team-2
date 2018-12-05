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

    /** The Fold button. */
    private JButton foldButton;

    /** The ALL IN button. */
    private JButton allInButton;

    private JTextField betAmount;

    private GameInfo gameInfo;


    public ControlPanel(BlockingQueue<Message> queue) {
        messageQueue = queue;
        newGameButton = createActionButton("NEW GAME");
        checkButton = createActionButton("CHECK");
        callButton = createActionButton("CALL");
        betButton = createActionButton("BET");
        raiseButton = createActionButton("RAISE");
        foldButton = createActionButton("FOLD");
//        allInButton = createActionButton("ALL_IN");
        betAmount = new JTextField(15);

        add(newGameButton);
    }

    public void setControlPanel(GameInfo gameInfo, String actionName) {
        this.gameInfo = gameInfo;
        clearAllButton();
        if (actionName.equals("NEW_GAME") && gameInfo.isOver()) {
            add(newGameButton);
        }
        else if (actionName.equals("NEW_GAME") || (actionName.equals("FOLD"))) {
            add(callButton);
            add(raiseButton);
        } else if (actionName.equals("CHECK")) {

            add(checkButton);
            add(betButton);
//            if (gameInfo.getCurrentPlayer().getMoney() <= gameInfo.getBigBlind()) {
//                add(allInButton);
//            }
        } else if(actionName.equals("CALL")) {
            add(checkButton);
            add(raiseButton);
        } else if (actionName.equals("BET") || actionName.equals("RAISE") || actionName.equals("ALL_IN")) {
            add(foldButton);
            add(callButton);
            add(raiseButton);
//            if (gameInfo.getCurrentPlayer().getMoney() <= gameInfo.getBigBlind()) {
//                add(allInButton);
//            }
        }
        betAmount.setText(Integer.toString(gameInfo.getBigBlind()));

        add(betAmount);
    }

    private void clearAllButton() {
        remove(newGameButton);
        remove(checkButton);
        remove(betButton);
        remove(raiseButton);
        remove(callButton);
        remove(foldButton);
//        remove(allInButton);
        remove(betAmount);
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
        if (buttonName.equals("ALL_IN")) button.addActionListener(new allInActionListener());
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

    public class allInActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new AllInactionMessage(gameInfo.getCurrentPlayer().getMoney()));
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
                messageQueue.put(new ActionCallMessage(gameInfo.getCurrentBet()));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class betActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionBetMessage(Integer.parseInt(betAmount.getText())));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public class raiseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                messageQueue.put(new ActionRaiseMessage(Integer.parseInt(betAmount.getText())));
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