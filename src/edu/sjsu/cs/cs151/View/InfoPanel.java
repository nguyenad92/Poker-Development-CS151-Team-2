package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Message.Message;
import edu.sjsu.cs.cs151.Model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class InfoPanel extends JPanel {

    private JLabel betLabel;

    private JLabel potLabel;

    private JPanel amountOfPot = new JPanel();

    private JPanel amountOfBet = new JPanel();

    private BlockingQueue<Message> messageQueue;

    private int potAmount = 0, betAmount = 0;

    public InfoPanel(BlockingQueue<Message> queue) {
        messageQueue = queue;

//        this.setBorder(TABLE_BORDER);
//        this.setBackground(TABLE_COLOR);
        this.setLayout(new GridBagLayout());

        JPanel infoPannel = new JPanel();
        this.add(infoPannel);

        amountOfPot.setLayout(new BorderLayout());
        amountOfPot.setForeground(Color.YELLOW);
        potLabel = new JLabel("Pot: " + potAmount);
        amountOfPot.add(potLabel, BorderLayout.NORTH);

        infoPannel.add(amountOfPot);

        betLabel = new JLabel("Current Bet: " + betAmount);
        amountOfBet.setLayout(new BorderLayout());
        amountOfBet.setForeground(Color.YELLOW);
        amountOfBet.add(betLabel, BorderLayout.NORTH);

        infoPannel.add(amountOfBet);
    }

    public void setInfoPannel(GameInfo gameInfo) {
        betAmount = gameInfo.getCurrentBet();
        potAmount = gameInfo.getPotTotal();

        potLabel.setText("Pot: " + potAmount);

        betLabel.setText("Current Bet: " + betAmount);
    }

    /**
     * Updates the current pot, bet, and community cards.
     * @param bet
     * @param pot
     */
    public void update(int bet, int pot) {
        if (bet == 0)
            betAmount = 0;
        else
            betAmount = bet;

        if (pot == 0)
            potAmount = 0;
        else
            potAmount = pot;

//        int communityCards = cards.size();
//        for (int i = 0; i < NO_OF_CARDS; i++) {
//            if (i < communityCards) {
//                int cardValue = cards.get(i).hashCode();
//                String link = String.format(IMAGE_LINK_FORMAT, cardValue);
//                ImageIcon cardImage = new ImageIcon(link);
//                cardLabels[i].setIcon(cardImage);
//            }
//            else {}
////                cardLabels[i].setIcon(CARD_FRAME_ICON);
//        }
    }

}
