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
        initInfoPanel();
    }

    public void setInfoPannel(GameInfo gameInfo) {
        betAmount = gameInfo.getCurrentBet();
        potAmount = gameInfo.getPotTotal();

        potLabel.setText("Pot: " + potAmount);
        betLabel.setText("Current Bet: " + betAmount);
    }

    private void initInfoPanel() {
        setLayout(new GridBagLayout());

        JPanel infoPannel = new JPanel();
        add(infoPannel);

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
}
