package edu.sjsu.cs.cs151.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Message.NewGameMessage;
import edu.sjsu.cs.cs151.Model.*;
 

/**
 * It shows players' cards, name, actions, and amount of money
 * @author ADNguyen
 *
 */
public class PlayerPanel extends JPanel {

	/**  Send information to the controller from the view*/
	private static BlockingQueue<Message> queue;

	/** The frame icon of the card */
//    private static final Icon CARD_FRAME_ICON = IconManager.getIcon("/images/card_frame.png");

//    /** The back of the card when it is up side down */
//    private static final Icon BACK_OF_CARD_ICON =
//    		IconManager.getIcon("/images/back_of_card.png");

    /** The format for the link to card images follow by its hashcode */
    private static final String IMAGE_LINK_FORMAT = "/images/card_%s.png";
    
    /**Border of player frame.
    * Square 10x10
    **/
    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    /** Show player's name */
    private JLabel playerNameLabel  = new JLabel();
    /** Show player's money */
    private JLabel playerMoneyLabel = new JLabel();
    /** Show card #1 of player */
    private JLabel card1Label = new JLabel();
    /** Show card #2 of player */
    private JLabel card2Label = new JLabel();
    
    /** Set the color of the table to green */
    public Color TABLE_COLOR = new Color(0, 128, 0);
    /** Set the color of text in player frame to yellow */
    public Color TEXT_COLOR = Color.YELLOW;

    /**
     * The panel show playeys' information included cards, name, and money 
     * @param blockingQueue
     */
    public PlayerPanel(BlockingQueue<Message> blockingQueue) {
    	queue = blockingQueue;
    	
    	this.setLayout(new GridLayout(2,6));
    	setBackground(Color.RED);

    	setBorder(BORDER);
    	setBackground(TABLE_COLOR);
    	
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gcl = new GridBagConstraints();
    	
//    	playerNameLabel = new
//    	
//    	card1Label = new JLabel("This is card 1");
//    	card2Label = new JLabel("This is card 2");


		// Player Info
    	gcl.gridx = 100;
    	gcl.gridy = 100;
    	gcl.gridwidth = 10;
    	gcl.gridheight = 10;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	add(playerNameLabel, gcl);


    	// Adding Money
    	gcl.gridx = 10;
    	gcl.gridy = 100;
    	gcl.gridwidth = 10;
    	gcl.gridheight = 10;

    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;

    	add(playerMoneyLabel, gcl);

    	// Card 1 Info
		gcl.gridx = 0;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;

    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;

    	gcl.weightx = 1;
    	gcl.weighty = 1;

    	add(card1Label, gcl);

		// Card 2 Info
    	gcl.gridx = 100;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;

    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;

    	gcl.weightx = 1;
    	gcl.weighty = 1;

    	add(card2Label, gcl);
    	
    }
    /**
     * The method that updates players' information
     * @param player
     */
    public void updateInfo(final GameInfo gameInfo ) {
    	for(int i = 0; i < gameInfo.getPlayerList().size(); i++) {
	    	Player player = gameInfo.getPlayerList().get(i);
    		playerNameLabel.setText(player.getName());
	    	playerMoneyLabel.setText("$  " + player.getMoney());
	    	ArrayList<Card> cards = player.getPlayerHands();
	    	
	    	ArrayList<Card> cards1 = player.getCard();
	    	    	
	    	int valueOfCard1 = cards1.get(0).hashCode();
	    	int valueOfCard2 = cards1.get(1).hashCode();
	    	
	    	String card1 = String.valueOf(valueOfCard1);
	    	String card2 = String.valueOf(valueOfCard2);
	    	
	    	String link1 = String.format(IMAGE_LINK_FORMAT, card1);
	    	String link2 = String.format(IMAGE_LINK_FORMAT, card2);
	    	
	    	ImageIcon cardImage1 = new ImageIcon(link1);
	    	ImageIcon cardImage2 = new ImageIcon(link2);
	    	
	    	
	    	if (cards1.size() == 2) {
	    		card1Label.setIcon(cardImage1);
	    		card2Label.setIcon(cardImage2);
	    	} else {
	    		card1Label.setIcon(new ImageIcon("/images/back_of_card.png"));
	    		card2Label.setIcon(new ImageIcon("/images/back_of_card.png"));
	    	}
    	}
    }

	private class BetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				queue.put(new NewGameMessage());
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
    
}
