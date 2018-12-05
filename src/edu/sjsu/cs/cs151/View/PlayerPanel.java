package edu.sjsu.cs.cs151.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
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
 *
 */
public class PlayerPanel extends JPanel {

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

	/** The label with the player's name. */
	private JLabel nameLabel;

	/** The label with the player's amount of cash. */
	private JLabel cashLabel;

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
	private final JLabel messageLabel;

	/**  Send information to the controller from the view*/
	private static BlockingQueue<Message> queue;

	/**Border of player frame.
	 * Square 10x10
	 **/
	private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
	/** Show player's name */
	private JLabel playerNameLabel  = new JLabel();
	private JLabel playerPositionLabel  = new JLabel();

	/** Show player's money */
	private JLabel playerMoneyLabel = new JLabel();
	/** Show card #1 of player */
//	private JLabel card1Label = new JLabel(IconManager.getIcon("/images/card_placeholder.png"));
	/** Show card #2 of player */
//	private JLabel card2Label = new JLabel(IconManager.getIcon("/images/card_placeholder.png"));

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

		setInTurn(false);
		setDealer(false);

	}
	/**
	 * The method that updates players' information
	 */
	public void updatePlayer1(final GameInfo gameInfo) {
		System.out.println(gameInfo.isOver());
		if (gameInfo.isOver()) {
			Player player = gameInfo.getDealerPlayer();
			ArrayList<Card> cards = player.getPlayerHands();
			for (int i = 0; i < 2; i++) {
				if (i > cards.size()) {
					card1Label.setIcon(IconManager.getIcon("/images/card_placeholder.png"));
					card2Label.setIcon(IconManager.getIcon("/images/card_placeholder.png"));
				}
			}
			betLabel.setText(" ");
			actionLabel.setText(" ");

		} else {
			if (gameInfo.getPlayerList().size() > 1) {
				Player player = gameInfo.getPlayerList().get(0);
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

				playerPositionLabel.setText(Integer.toString(player.getCurrentPositionOnTable()));

				ArrayList<Card> cards = player.getPlayerHands();

				card1Label.setIcon(IconManager.getCardImage(cards.get(0)));
				card2Label.setIcon(IconManager.getCardImage(cards.get(1)));
			}
		}
	}

	public void updatePlayer2(final GameInfo gameInfo) {
		if (gameInfo.isOver()) {
			Player player = gameInfo.getBigBlindPlayer();
			ArrayList<Card> cards = player.getPlayerHands();
			for (int i = 0; i < 2; i++) {
				if (i > cards.size()) {
					card1Label.setIcon(IconManager.getIcon("/images/card_placeholder.png"));
					card2Label.setIcon(IconManager.getIcon("/images/card_placeholder.png"));
				}
			}
			betLabel.setText(" ");
			actionLabel.setText(" ");

		} else {
			if (gameInfo.getPlayerList().size() > 1) {
				Player player = gameInfo.getPlayerList().get(1);
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

				ArrayList<Card> cards = player.getPlayerHands();

				card1Label.setIcon(IconManager.getCardImage(cards.get(0)));
				card2Label.setIcon(IconManager.getCardImage(cards.get(1)));
			}
		}
	}

	public void setPlayerPanel(GameInfo gameInfo) {
		updatePlayer1(gameInfo);
	}



	/**
	 * Sets whether the player is the dealer.
	 *
	 * @param isDealer
	 *            True if the dealer, otherwise false.
	 */
	public void setDealer(boolean isDealer) {
		if (isDealer) {
			dealerButton.setIcon(BUTTON_PRESENT_ICON);
		} else {
			dealerButton.setIcon(BUTTON_ABSENT_ICON);
		}
	}

	/**
	 * Sets whether it's this player's turn to act.
	 *
	 * @param inTurn
	 *            True if it's the player's turn, otherwise false.
	 */
	public void setInTurn(boolean inTurn) {
		if (inTurn) {
			playerNameLabel.setForeground(Color.YELLOW);
		} else {
			playerNameLabel.setForeground(Color.GREEN);
		}
	}

	public void setMessage(String message) {
		if (message.length() == 0) {
			messageLabel.setText(" ");
		} else {
			messageLabel.setText(message);
		}
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