package edu.sjsu.cs.cs151.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.sjsu.cs.cs151.model.*;
/**
 * it shows community cards, amount of pots, current actions, messages
 * it needs to update informations when pot increases or player do betting,...
 * @author ADNguyen
 *
 */
public class TableFrame extends JPanel
{
	/** The maximum number of community cards. */
    private static final int NO_OF_CARDS = 5;
    
    /** The control panel. */
    private final ActionListener actionListener;
    
    /** Label with the bet. */
    private final JLabel betLabel;

    /** Label with the pot. */
    private final JLabel potLabel;

    /** Labels with the community cards. */
    private final JLabel[] cardLabels;
    
    /** Label with a custom message. */
    private final JLabel messageLabel;
    
    /**
     * Constructor.
     * The control panel.
     */
    public TableFrame(ActionListener actionListener) {
        this.actionListener = actionListener;
        
        setBorder(UIConstants.PANEL_BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        JLabel label = new JLabel("Bet");
        label.setForeground(Color.GREEN);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(0, 5, 0, 5);
        add(label, gc);
        
        label = new JLabel("edu.sjsu.cs.cs151.model.Pot");
        label.setForeground(Color.GREEN);
        gc.gridx = 3;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(0, 5, 0, 5);
        add(label, gc);
        
        betLabel = new JLabel(" ");
        betLabel.setBorder(UIConstants.LABEL_BORDER);
        betLabel.setForeground(Color.GREEN);
        betLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(5, 5, 5, 5);
        add(betLabel, gc);

        potLabel = new JLabel(" ");
        potLabel.setBorder(UIConstants.LABEL_BORDER);
        potLabel.setForeground(Color.GREEN);
        potLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 3;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 0.0;
        gc.insets = new Insets(5, 5, 5, 5);
        add(potLabel, gc);

        // The five card positions.
        cardLabels = new JLabel[NO_OF_CARDS];
        for (int i = 0; i < 5; i++) {
            cardLabels[i] = new JLabel(IconManager.getIcon("/images/card_placeholder.png"));
            gc.gridx = i;
            gc.gridy = 2;
            gc.gridwidth = 1;
            gc.gridheight = 1;
            gc.anchor = GridBagConstraints.CENTER;
            gc.fill = GridBagConstraints.NONE;
            gc.weightx = 0.0;
            gc.weighty = 0.0;
            gc.insets = new Insets(5, 1, 5, 1);
            add(cardLabels[i], gc);
        }
        
        // Message label.
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 5;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.insets = new Insets(0, 0, 0, 0);
        add(messageLabel, gc);
        
        // Control panel.
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 5;
        gc.gridheight = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(this.actionListener, gc);
        
        setPreferredSize(new Dimension(400, 270));
        
        update(null, 0, 0);
    }
    
    /**
     * Updates the current hand status.
     * 
     * @param bet
     * @param pot
     */
    public void update(List<Card> cards, int bet, int pot) {
        if (bet == 0)
            betLabel.setText(" ");
        else
            betLabel.setText("$ " + bet);
        
        if (pot == 0)
            potLabel.setText(" ");
        else
            potLabel.setText("$ " + pot);
       
        int noOfCards = (cards == null) ? 0 : cards.size();
        for (int i = 0; i < NO_OF_CARDS; i++)
        {
            if (i < noOfCards)
                cardLabels[i].setIcon(IconManager.getCardImage(cards.get(i)));
            else
                cardLabels[i].setIcon(IconManager.getIcon("/images/card_placeholder.png"));
           
        }
    }
    
    /**
     * Sets a custom message.
     * 
     * @param message
     *            The message.
     */
    public void setMessage(String message) {
        if (message.length() == 0) {
            messageLabel.setText(" ");
        } else {
            messageLabel.setText(message);
        }
    }
    
    /**
     * Waits for the user to continue.
     */
    public void waitForUserInput() {
        controlPanel.waitForUserInput();
    }
}
