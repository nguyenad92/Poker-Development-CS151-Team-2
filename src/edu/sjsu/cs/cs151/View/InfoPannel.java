package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InfoPannel extends JPanel {

    private JLabel betLabel;

    private JLabel potLabel;

    private JPanel amountOfPot = new JPanel();

    private JPanel amountOfBet = new JPanel();

    private Controller baseController;

    public InfoPannel() {

//        this.setBorder(TABLE_BORDER);
//        this.setBackground(TABLE_COLOR);
        this.setLayout(new GridBagLayout());

        JPanel infoPannel = new JPanel();
        this.add(infoPannel);

        amountOfPot.setLayout(new BorderLayout());
        amountOfPot.setForeground(Color.YELLOW);
        potLabel = new JLabel("Pot: 0");
        amountOfPot.add(potLabel, BorderLayout.NORTH);

        infoPannel.add(amountOfPot);

        betLabel = new JLabel("Bet: 0");
        amountOfBet.setLayout(new BorderLayout());
        amountOfBet.setForeground(Color.YELLOW);
        amountOfBet.add(betLabel, BorderLayout.NORTH);

        infoPannel.add(amountOfBet);
    }

    public void setInfoPannel(GameInfo gameInfo) {

    }

    /**
     * Updates the current pot, bet, and community cards.
     * @param cards
     * @param bet
     * @param pot
     */
    public void update(List<Card> cards, int bet, int pot) {
        if (bet == 0)
            betLabel.setText("0");
        else
            betLabel.setText("$ " + bet);

        if (pot == 0)
            potLabel.setText("0");
        else
            potLabel.setText("$ " + pot);

        int communityCards = cards.size();
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
