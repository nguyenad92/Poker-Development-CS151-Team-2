package edu.sjsu.cs.cs151.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Message.Message;

/**
 * it shows community cards, amount of pots, current actions, messages
 * it needs to update informations when pot increases or player do betting,...
 * @author ADNguyen
 *
 */
public class TablePanel extends JPanel {
    //number of community cards
    private static final int NO_OF_CARDS = 5;

//    private static final Icon CARD_FRAME_ICON =
//    		IconManager.getIcon("/images/card_frame.png");

    private static final String IMAGE_LINK_FORMAT = "/images/card_%s.png";


//    private PlayerPanel playerPanel = new PlayerPanel(queue"Calvin", "1000");

    private final JLabel[] cardLabels;

    private final JLabel messageLabel = new JLabel();

    private static final Border TABLE_BORDER
            = new CompoundBorder(new LineBorder(Color.BLACK, 1), new EmptyBorder(10, 10, 10, 10));

    private static final Border LABEL_BORDER = new LineBorder(Color.BLACK, 1);

//    public Color TABLE_COLOR = new Color(0, 128, 0);

    /**
     * Constructor.
     */
    public TablePanel() {

        this.setBorder(TABLE_BORDER);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gcl = new GridBagConstraints();


        /**
         * Info Pannel
         */
        InfoPannel infoPannel = new InfoPannel();
        this.add(infoPannel);

        /**
         * Community Card Pannel
         */
        // The five card positions.
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 5));

        cardLabels = new JLabel[NO_OF_CARDS];
        for (int i = 0; i < NO_OF_CARDS; i++) {
//            cardLabels[i] = new JLabel(CARD_FRAME_ICON);
            cardLabels[i] = new JLabel();
            cardPanel.add(cardLabels[i]);
        }

        this.add(cardPanel);
        this.setPreferredSize(new Dimension(400,270));


        /**
         * Player pannel
         */
//        this.add(playerPanel);

        /**
         * ControllPannel
         */
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel);

        JButton checkbutton = new JButton("Check");
        buttonPanel.add(checkbutton);
        checkbutton.setBounds(0,260,100,40);

        JButton callbutton = new JButton("Call");
        buttonPanel.add(callbutton);
        callbutton.setBounds(100,260,100,40);

        JButton raisebutton = new JButton("Raise");
        buttonPanel.add(raisebutton);
        raisebutton.setBounds(200,260,100,40);

        JButton foldbutton = new JButton("Fold");
        buttonPanel.add(foldbutton);
        foldbutton.setBounds(300,260,100,40);

        JButton betbutton = new JButton("Bet");
        buttonPanel.add(betbutton);
        betbutton.setBounds(400,260,100,40);


//        checkbutton.addActionListener(new checkActionListener());
        callbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        raisebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        foldbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        betbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        update(null, 0, 0);
    }



    public void setMessage(String message)
    {
        messageLabel.setText(message);
    }

    public JLabel getMessage()
    {
        return messageLabel;
    }

}

