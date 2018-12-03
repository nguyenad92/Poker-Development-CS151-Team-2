package edu.sjsu.cs.cs151.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Model.*;
/**
 * it shows community cards, amount of pots, current actions, messages
 * it needs to update informations when pot increases or player do betting,...
 * @author ADNguyen
 *
 */
public class TableFrame extends JPanel {
	//number of community cards
    private static final int NO_OF_CARDS = 5;
    
//    private static final Icon CARD_FRAME_ICON =
//    		IconManager.getIcon("/images/card_frame.png");
    
    private static final String IMAGE_LINK_FORMAT = "/images/card_%s.png";
    
    private final JLabel betLabel = new JLabel();

    private final JLabel potLabel = new JLabel();

    private final JLabel[] cardLabels;

    private final JLabel messageLabel = new JLabel();

    private final JPanel amountOfPot;
    
    private final JPanel amountOfBet;

    private TablePanel basePannel;
    
    private static final Border TABLE_BORDER 
    	= new CompoundBorder(new LineBorder(Color.BLACK, 1), new EmptyBorder(10, 10, 10, 10));
    
    private static final Border LABEL_BORDER = new LineBorder(Color.BLACK, 1);
    		
    public Color TABLE_COLOR = new Color(0, 128, 0);
    /**
     * Constructor.
     */
    public TableFrame() {
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(TABLE_BORDER);
        tablePanel.setBackground(TABLE_COLOR);
        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gcl = new GridBagConstraints();
        
       
        amountOfPot = new JPanel();
        amountOfPot.setLayout(new BorderLayout());
        amountOfPot.setForeground(Color.YELLOW);
        setMessage("Pot: 0");
        amountOfPot.add(getMessage(), BorderLayout.CENTER);
        
        tablePanel.add(amountOfPot);
        
        amountOfBet = new JPanel();
        amountOfBet.setLayout(new BorderLayout());
        amountOfBet.setForeground(Color.YELLOW);
        setMessage("Bet: 0");
        amountOfBet.add(getMessage(), BorderLayout.CENTER);
        
        tablePanel.add(amountOfBet);

        // The five card positions.
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 5));
        
        cardLabels = new JLabel[NO_OF_CARDS];
        for (int i = 0; i < NO_OF_CARDS; i++) {
//            cardLabels[i] = new JLabel(CARD_FRAME_ICON);
            cardPanel.add(cardLabels[i]);
        }
        
        tablePanel.add(cardPanel);
        tablePanel.setPreferredSize(new Dimension(400,270));
        
        update(null, 0, 0);
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
        for (int i = 0; i < NO_OF_CARDS; i++)
        {
            if (i < communityCards)
            {
            	int cardValue = cards.get(i).hashCode();
            	String link = String.format(IMAGE_LINK_FORMAT, cardValue);
            	ImageIcon cardImage = new ImageIcon(link);
            	cardLabels[i].setIcon(cardImage);
            }
                
            else {}
//                cardLabels[i].setIcon(CARD_FRAME_ICON);
           
        }
    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Poker");
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 300);
//        JPanel panel = new JPanel();
//        frame.add(panel);
//        JButton checkbutton = new JButton("Check");
//        panel.add(checkbutton);
//        checkbutton.setBounds(0,260,100,40);
//        JButton callbutton = new JButton("Call");
//        panel.add(callbutton);
//        callbutton.setBounds(100,260,100,40);
//        JButton raisebutton = new JButton("Raise");
//        panel.add(raisebutton);
//        raisebutton.setBounds(200,260,100,40);
//        JButton foldbutton = new JButton("Fold");
//        panel.add(foldbutton);
//        foldbutton.setBounds(300,260,100,40);
//        JButton betbutton = new JButton("Bet");
//        panel.add(betbutton);
//        betbutton.setBounds(400,260,100,40);
//        checkbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        callbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        raisebutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        foldbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        betbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        JLabel Pot = new JLabel("", (int) JLabel.LEFT_ALIGNMENT);
//        JLabel CurrentBet = new JLabel("", (int) JLabel.RIGHT_ALIGNMENT);
//    }
    
    public void setMessage(String message) 
    {
    	messageLabel.setText(message);
    }
    
    public JLabel getMessage()
    {
    	return messageLabel;
    }
    
}
