package edu.sjsu.cs.cs151.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.sjsu.cs.cs151.Message.Message;

public class MainFrame extends JFrame{
	
	private JPanel topPanel, cardPanel, buttonPanel,
			playerPanel1, playerPanel2 ;
	
	private static BlockingQueue<Message> queue = null;
	
	private static GamePanel gamePanel = null;
	
	public static final int DEFAULT_HEIGHT = 900;
	
	public static final int DEFAULT_WIDTH = 600;
	
	public static final int NO_OF_CARDS = 5;
	
	public static GamePanel init(BlockingQueue<Message> blockingQueue) {
		if(gamePanel == null) {
			queue = blockingQueue;
			gamePanel = new GamePanel();
		}
		
		return gamePanel;
	}
	
	private void GamePanel() {
		setFrameDisplay();
		setLocationPanel();
		setVisible(true);
		
	}
	
	public void setFrameDisplay() {
		setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setLocationPanel() {
		Container mainContainer = new Container();
		mainContainer.setLayout(new BorderLayout());
		
		topPanel = new InfoPanel(null);
		mainContainer.add(topPanel, BorderLayout.NORTH);
		
//		buttonPanel = new ControlPanel();
//		mainContainer.add(buttonPanel, BorderLayout.SOUTH);
		
		cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 5));
        
        JLabel[] cardLabels = new JLabel[NO_OF_CARDS];

        for (int i = 0; i < NO_OF_CARDS; i++) {
            cardLabels[i] = new JLabel("/images/card_frame.png");
            cardLabels[i] = new JLabel();
            cardPanel.add(cardLabels[i]);
        }
        
        mainContainer.add(cardPanel, BorderLayout.CENTER);
        

		playerPanel1 = new PlayerPanel(null);
		mainContainer.add(playerPanel1, BorderLayout.WEST);
		
		playerPanel2 = new PlayerPanel(null);
		mainContainer.add(playerPanel2, BorderLayout.EAST);
		
		
	}
	public void setPlayerPanel() {
		
		
	}
	
	
	
}
