package edu.sjsu.cs.cs151.View;

import java.util.List;

import javax.swing.*;

import edu.sjsu.cs.cs151.Model.Card;

/**
 * Community Card + text that describe the state game action of previous players
 */
public class GamePanel extends JPanel {
	
	private static final int NO_OF_CARDS = 5;
	
	private static final JLabel messageLabel = null;

    public void setGamePanel(GameInfo gameInfo) {
    	setCommunityCards(null);
    	updateMessage("Hello");
    	
    }
    
    public void setCommunityCards(List<Card> cards) {
    	JLabel[] cardLabels = null;
    	int communityCards = cards.size();
    	for (int i = 0; i < NO_OF_CARDS; i++) {
    		if (i < communityCards) {
    			int cardValue = cards.get(i).hashCode();
    			String link = String.format("/images/card_%s.png", cardValue);
    			ImageIcon cardImage = new ImageIcon(link);
    			cardLabels[i].setIcon(cardImage);
    		}
    		else {
    			cardLabels[i].setIcon(new ImageIcon("/images/card_frame.png"));
    		}
      }

    }
    
    public void updateMessage(String message) {
    	if(message.length() == 0)
    		messageLabel.setText(" ");
    	else
    		messageLabel.setText("message");
    }

}
