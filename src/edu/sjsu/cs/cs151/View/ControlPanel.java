package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.ActionCheckMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

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

    public ControlPanel() {
        checkButton = createActionButton("CHECK");
        callButton = createActionButton("CALL");
        betButton = createActionButton("BET");
        raiseButton = createActionButton("RAISE");
        foldButton = createActionButton("FOLD");
    }

    public void setControlPanel(String actionName) {

    }

    private JButton createActionButton(String buttonName) {
        String label = buttonName;
        JButton button = new JButton(label);
        button.setMnemonic(label.charAt(0));
        button.setSize(100, 30);
        button.addActionListener(new checkActionListener());
        return button;
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

}
