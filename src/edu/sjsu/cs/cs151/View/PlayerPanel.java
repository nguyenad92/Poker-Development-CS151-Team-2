package edu.sjsu.cs.cs151.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.sjsu.cs.cs151.Model.*;
 

/**
 * It shows players' cards, name, actions, and amount of money
 * @author ADNguyen
 *
 */
public class PlayerPanel extends JPanel {
	
    private static final Icon CARD_FRAME_ICON =
    		IconManager.getIcon("/images/card_frame.png");

    private static final Icon BACK_OF_CARD_ICON =
    		IconManager.getIcon("/images/back_of_card.png");
    
    private static final String IMAGE_LINK_FORMAT = "/images/card_%s.png";
    
    /**Border of player frame.
    * Square 10x10
    **/
    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    
    private JLabel playerNameLabel  = new JLabel();
    
    private JLabel playerMoneyLabel = new JLabel();
    
    private JLabel card1Label = new JLabel();
    
    private JLabel card2Label = new JLabel();
    
    //Green table
    public Color TABLE_COLOR = new Color(0, 128, 0);
    //Yellow text color
    public Color TEXT_COLOR = Color.YELLOW;

    public PlayerPanel(String name, String money) {
    	playerNameLabel.setText(name);
    	playerMoneyLabel.setText("$" + money);
    	setBorder(BORDER);
    	setBackground(TABLE_COLOR);
    	
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gcl = new GridBagConstraints();
    	
//    	playerNameLabel = new
    	
    	card1Label = new JLabel("This is card 1");
    	card2Label = new JLabel("This is card 2");


		// Player Info
    	gcl.gridx = 1000;
    	gcl.gridy = 100000;
    	gcl.gridwidth = 10;
    	gcl.gridheight = 10;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	add(playerNameLabel, gcl);


    	// Adding Money
    	gcl.gridx = 1000;
    	gcl.gridy = 1000;
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
    	gcl.gridx = 1000000;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;

    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;

    	gcl.weightx = 1;
    	gcl.weighty = 1;

    	add(card2Label, gcl);
    	
    }
    
    public void updateInfo(Player player) {
    	playerNameLabel.setText( player.getName());
    	playerMoneyLabel.setText("$  " + Integer.toString(player.getMoney()));
    	ArrayList<Card> cards = player.getPlayerHands();
    	    	
    	int valueOfCard1 = cards.get(0).hashCode();
    	int valueOfCard2 = cards.get(1).hashCode();
    	
    	String card1 = String.valueOf(valueOfCard1);
    	String card2 = String.valueOf(valueOfCard2);
    	
    	String link1 = String.format(IMAGE_LINK_FORMAT, card1);
    	String link2 = String.format(IMAGE_LINK_FORMAT, card2);
    	
    	ImageIcon cardImage1 = new ImageIcon(link1);
    	ImageIcon cardImage2 = new ImageIcon(link2);
    	
    	
    	if (cards.size() == 2) {
    		card1Label.setIcon(cardImage1);
    		card2Label.setIcon(cardImage2);
    	} else {
    		card1Label.setIcon(BACK_OF_CARD_ICON);
    		card2Label.setIcon(BACK_OF_CARD_ICON);
    	}
    		
    }
    
}
