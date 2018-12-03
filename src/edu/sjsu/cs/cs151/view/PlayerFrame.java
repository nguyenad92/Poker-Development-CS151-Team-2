package edu.sjsu.cs.cs151.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;
import javax.swing.ImageIcon;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.sjsu.cs.cs151.model.*;
 

/**
 * It shows players' cards, name, actions, and amount of money
 * @author ADNguyen
 *
 */
public class PlayerFrame extends JPanel
{
	
    private static final Icon CARD_FRAME_ICON =
    		IconManager.getIcon("/images/card_frame.png");

    private static final Icon BACK_OF_CARD_ICON =
    		IconManager.getIcon("/images/back_of_card.png");
    
    private static final String IMAGE_LINK_FORMAT = "/images/card_%s.png";
    
    /**Border of player frame.
    * Square 10x10
    **/
    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    
    private JLabel playerNameLabel;
    
    private JLabel playerMoneyLabel;
    
    private JLabel card1Label;
    
    private JLabel card2Label;
    
    //Green table
    public Color TABLE_COLOR = new Color(0, 128, 0);
    //Yellow text color
    public Color TEXT_COLOR = Color.YELLOW;
    
    
    
    public PlayerFrame(String name, String money)
    {
    	playerNameLabel.setText(name);
    	playerMoneyLabel.setText(money);
    	setBorder(BORDER);
    	setBackground(TABLE_COLOR);
    	
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gcl = new GridBagConstraints();
    	
//    	playerNameLabel = new
    	
    	card1Label = new JLabel(CARD_FRAME_ICON);
    	card2Label = new JLabel(CARD_FRAME_ICON);
    	
    	gcl.gridx = 0;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	gcl.weightx = 1;
    	gcl.weighty = 1;
    	
    	add(playerNameLabel, gcl);
    	
    	gcl.gridx = 0;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	gcl.weightx = 1;
    	gcl.weighty = 1;
    	
    	add(playerMoneyLabel, gcl);
    	
    	gcl.gridx = 0;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	gcl.weightx = 1;
    	gcl.weighty = 1;
    	
    	add(card1Label, gcl);
    	
    	gcl.gridx = 0;
    	gcl.gridy = 1;
    	gcl.gridwidth = 1;
    	gcl.gridheight = 1;
    	
    	gcl.anchor = GridBagConstraints.CENTER;
    	gcl.fill = GridBagConstraints.HORIZONTAL;
    	
    	gcl.weightx = 1;
    	gcl.weighty = 1;
    	
    	add(card2Label, gcl);
    	
    }
    
    public void updateInfo(Player player)
    {
    	playerNameLabel.setText( player.getName());
    	playerMoneyLabel.setText("$  " + Integer.toString(player.getMoney()));
    	Card[] cards = player.getPlayerHands();
    	    	
    	int valueOfCard1 = cards[0].hashCode();
    	int valueOfCard2 = cards[1].hashCode();
    	
    	String card1 = String.valueOf(valueOfCard1);
    	String card2 = String.valueOf(valueOfCard2);
    	
    	String link1 = String.format(IMAGE_LINK_FORMAT, card1);
    	String link2 = String.format(IMAGE_LINK_FORMAT, card2);
    	
    	ImageIcon cardImage1 = new ImageIcon(link1);
    	ImageIcon cardImage2 = new ImageIcon(link2);
    	
    	
    	if(cards.length == 2)
    	{
    		card1Label.setIcon(cardImage1);
    		card2Label.setIcon(cardImage2);
    	}
    	else
    	{
    		card1Label.setIcon(BACK_OF_CARD_ICON);
    		card2Label.setIcon(BACK_OF_CARD_ICON);
    	}
    		
    }
    	
    
}
