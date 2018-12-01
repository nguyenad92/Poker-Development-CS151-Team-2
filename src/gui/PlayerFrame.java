package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.sjsu.cs.cs151.Card;
import edu.sjsu.cs.cs151.Player;
 

/**
 * It shows player' cards, name, actions, and amount of money
 * @author ADNguyen
 *
 */
public class PlayerFrame extends JPanel
{
	/**
	 * Auto generate serialization to avoid conflict of UI
	 */
	
	private static final long serialVersionUID = 807421424185027815L;

	/** Filled dealer button image when player is dealer. */
    private static final Icon BUTTON_PRESENT_ICON =
            IconManager.getIcon("/images/button_present.png");
    
    /** Empty dealer button image when player is not dealer. */
    private static final Icon BUTTON_ABSENT_ICON =
    		IconManager.getIcon("/images/button_absent.png");
    
    private static final Icon CARD_PLACEHOLDER_ICON =
    		IconManager.getIcon("/images/card_placeholder.png");

    private static final Icon CARD_BACK_ICON =
    		IconManager.getIcon("/images/card_back.png");
    
    /** The border. */
    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    
    /** The label with the player's name. */
    private JLabel nameLabel;
    
    /** The label with the player's amount of cash. */
    private JLabel cashLabel;
    
    /** The label with the last action performed. */
    private JLabel actionLabel;
    
    /** The label with the player's current bet. */
    private JLabel betLabel;

    /** The label for the first card. */
    private JLabel card1Label;

    /** The label for the second card. */
    private JLabel card2Label;

    /** The label for the dealer button image. */
    private JLabel dealerButton;
    
    public PlayerFrame() {
        setBorder(BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        nameLabel = new MyLabel();
        cashLabel = new MyLabel();
        actionLabel = new MyLabel();
        betLabel = new MyLabel();
        card1Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card2Label = new JLabel(CARD_PLACEHOLDER_ICON);
        dealerButton = new JLabel(BUTTON_ABSENT_ICON);
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(dealerButton, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.insets = new Insets(1, 1, 1, 1);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(cashLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(actionLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(betLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card1Label, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card2Label, gc);

        setInTurn(false);
    }
   
    
    /**
     * Updates the player's panel.
     * 
     * @param player
     */
    public void update(Player player) {
        nameLabel.setText(player.getName());
        cashLabel.setText("$ " + player.getMoney());
        int bet = player.getCurrentBet();
        if (bet == 0) 
            betLabel.setText(" ");
        else
            betLabel.setText("$ " + bet);
        
//        Action action = player.getAction();
//        if (action != null) {
//            actionLabel.setText(action.getName());
//        } else {
//            actionLabel.setText(" ");
//        }
   
        Card[] cards = player.getCards();
        if (cards.length == 2) 
        {
            // Visible cards.
            card1Label.setIcon(IconManager.getCardImage(cards[0]));
            card2Label.setIcon(IconManager.getCardImage(cards[1]));
        }
        else 
        {
        // No cards.
        card1Label.setIcon(CARD_PLACEHOLDER_ICON);
        card2Label.setIcon(CARD_PLACEHOLDER_ICON);
        }
    }
    
    
    /**
     * Sets whether it's this player's turn to act.
     * 
     * @param inTurn
     *            True if it's the player's turn, otherwise false.
     */
    public void setInTurn(boolean inTurn) 
    {
        if (inTurn)
            nameLabel.setForeground(Color.YELLOW);
        else 
            nameLabel.setForeground(Color.GREEN);
    }
    
    private static class MyLabel extends JLabel 
    {
        /**
		 * Auto generate serializtion to avoid conflict UI
		 */
		private static final long serialVersionUID = 1199520141866606128L;

		/**
         * Constructor.
         */
        public MyLabel() {
            setBorder(UIConstants.LABEL_BORDER);
            setForeground(UIConstants.TEXT_COLOR);
            setHorizontalAlignment(JLabel.HORIZONTAL);
            setText(" ");
        }
        
    }
    
    
}
