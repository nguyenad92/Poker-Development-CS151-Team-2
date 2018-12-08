package edu.sjsu.cs.cs151.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import javax.swing.Icon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.*;


/**
 * Class that shows players' cards, name, actions, and amount of money
 */
public class PlayerPanel extends JPanel {

	/** Empty dealer button image when player is not dealer. */
	private static final Icon BUTTON_ABSENT_ICON =
			IconManager.getIcon("/images/button_absent.png");

	private static final Icon CARD_PLACEHOLDER_ICON =
			IconManager.getIcon("/images/card_placeholder.png");

	private static final Icon CARD_BACK_ICON =
			IconManager.getIcon("/images/card_back.png");

	/** The label with the last action performed. */
	private JLabel actionLabel;

	/** The label with the player's current bet. */
	private JLabel betLabel;

	/** The label for the first hole card. */
	private JLabel card1Label;

	/** The label for the second hole card. */
	private JLabel card2Label;

	/** The label for the dealer button image. */
	private JLabel dealerButton;

	/** Label with a custom message. */
	private JLabel messageLabel;

	/**  Send information to the controller from the view*/
	private static BlockingQueue<Message> queue;

	/** Player that contains its information */
	private Player player;

	/**Border of player frame.
	 * Square 10x10
	 **/
	private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);

	/** Show player's name */
	private JLabel playerNameLabel;
	private JLabel playerPositionLabel;

	/** Show player's money */
	private JLabel playerMoneyLabel;

	/**
	 * The panel show playeys' information included cards, name, and money
	 */
	public PlayerPanel() {
		initPlayerPanel();
	}
	/**
	 * The method that updates players' information
	 */
	public void setPlayerPanel(GameInfo gameInfo, int position) {
		if(gameInfo.isShowDown())
		{
			for(int i = 0 ; i < gameInfo.getPlayerList().size(); i++)
			{
				player = gameInfo.getPlayerList().get(i);
				ArrayList<Card> cards = player.getPlayerHands();
				card1Label.setIcon(IconManager.getCardImage(cards.get(0)));
				card2Label.setIcon(IconManager.getCardImage(cards.get(1)));
				playerNameLabel.setText(player.getName());
				playerNameLabel.setForeground(UIConstants.TEXT_COLOR);
				playerMoneyLabel.setText("$ " + player.getMoney());
			}
		}
		else
		{
			player = gameInfo.getCurrentActor();
			ArrayList<Card> cards = player.getPlayerHands();

			if (gameInfo.getPlayerList().size() > 0) {
				// Display Card
				if (gameInfo.getPlayerList().get(position).equals(gameInfo.getCurrentPlayer())) {
					card1Label.setIcon(IconManager.getCardImage(cards.get(0)));
					card2Label.setIcon(IconManager.getCardImage(cards.get(1)));
				} else {        // Hide Card
					card1Label.setIcon(CARD_BACK_ICON);
					card2Label.setIcon(CARD_BACK_ICON);
				}
			} else {
				card1Label.setIcon(CARD_PLACEHOLDER_ICON);
				card2Label.setIcon(CARD_PLACEHOLDER_ICON);
			}

			playerNameLabel.setText(player.getName());
			playerNameLabel.setForeground(UIConstants.TEXT_COLOR);
			playerMoneyLabel.setText("$ " + player.getMoney());

			if (gameInfo.getCurrentPlayerBet() == 0) {
				betLabel.setText(" ");
			} else {
				betLabel.setText("$ " + player.getCurrentBet());
			}

			if (gameInfo.getCurrentPlayerAction().equals("")) {
				actionLabel.setText(" ");
			} else {
				actionLabel.setText(player.getCurrentAction());
			}
		}

	}

	/**
	 * Setup the message for users
	 * @param message
	 */
	public void setMessage(String message) {
		if (message.length() == 0) {
			messageLabel.setText(" ");
		} else {
			messageLabel.setText(message);
		}
	}

	/**
	 * Design the PlayerPanel UI
	 * @author Oscar Stigter
	 */
	private void initPlayerPanel() {
		setBorder(BORDER);
		setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		playerNameLabel = new MyLabel();
		playerMoneyLabel = new MyLabel();
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

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.insets = new Insets(1, 1, 1, 1);
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		add(playerNameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(playerMoneyLabel, gc);
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
	}

	/**
	 * Custom label for a player panel.
	 *
	 * @author Oscar Stigter
	 */
	private static class MyLabel extends JLabel {

		/** Serial version UID. */
		private static final long serialVersionUID = 3607645928062082095L;

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