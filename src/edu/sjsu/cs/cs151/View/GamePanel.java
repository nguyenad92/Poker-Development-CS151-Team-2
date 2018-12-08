package edu.sjsu.cs.cs151.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Model.Card;

/**
 * Class that shows Community Cards and text that describe the state game action of previous players
 */
public class GamePanel extends JPanel {
	
	private static final int NO_OF_CARDS = 5;

	BlockingQueue<Message> messageQueue;

	private JLabel[] cardLabels;

	private JLabel messageLabel = null;

	/**
	 * Construct the class
	 * @param queue
	 */
	public GamePanel(BlockingQueue<Message> queue) {
		messageQueue = queue;

		setBorder(UIConstants.PANEL_BORDER);
		setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

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
			gc.weightx = 1;
			gc.weighty = 1;
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
	}

	/**
	 * Set up the community cards panel
	 * @param gameInfo
	 */
    public void setGamePanel(GameInfo gameInfo) {
			addCommunityCards(gameInfo.getCommunityCards());
    }

	/**
	 * Adding the community cards into the panel
	 * @param cards
	 */
	public void addCommunityCards(ArrayList<Card> cards) {
		int noOfCards = cards.size();
		for (int i = 0; i < NO_OF_CARDS; i++) {
			if (i < noOfCards) {
				cardLabels[i].setIcon(IconManager.getCardImage(cards.get(i)));
			} else {
				cardLabels[i].setIcon(IconManager.getIcon("/images/card_placeholder.png"));
			}
		}
	}

	/**
	 * Showing the message on the table
	 * @param gameInfo
	 * @param message
	 */
    public void setMessage(GameInfo gameInfo, String message) {
    	if(message.length() == 0)
    		messageLabel.setText(" ");
    	else if(gameInfo.isStarted())
    		messageLabel.setText("Game Start!!!" + gameInfo.getCurrentPlayer().getName() + " go first!");
    	else if(gameInfo.isOver())
		{
			for(int i = 0; i < gameInfo.getPlayerList().size(); i++)
				if(gameInfo.getPlayerList().get(i).isWinner())
					messageLabel.setText("Winner is " + gameInfo.getPlayerList().get(i).getName());
				else
					messageLabel.setText(("Game Over"));
		}
		else
    		messageLabel.setText(message);
    }

}
