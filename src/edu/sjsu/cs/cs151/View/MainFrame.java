package edu.sjsu.cs.cs151.View;
import edu.sjsu.cs.cs151.Message.Message;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.sjsu.cs.cs151.Message.Message;

public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private JPanel cardPanel, controlPanel,
            playerPanel1, playerPanel2, infoPanel, gamePanel;

    public static final int DEFAULT_HEIGHT = 900;

    public static final int DEFAULT_WIDTH = 600;

    public static final int NO_OF_CARDS = 5;

    public MainFrame() {

//        PlayerPannel = new PlayerPanel(messageQueue, "Calvin", 10000);
        controlPanel = new ControlPanel(messageQueue);
        infoPanel = new InfoPanel(messageQueue);
        gamePanel = new GamePanel(messageQueue);
        playerPanel1 = new PlayerPanel(messageQueue);
        playerPanel2 = new PlayerPanel(messageQueue);
        setupFrame();
    }

    public static MainFrame init(BlockingQueue<Message> queue) {
        if (mainFrame == null) {
            messageQueue = queue;
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    public void setPlayerPannel(GameInfo gameInfo) {
    	((PlayerPanel) playerPanel1).updatePlayer1(gameInfo);
    	((PlayerPanel) playerPanel2).updatePlayer2(gameInfo);
    }

    public void setControlPanel(GameInfo gameInfo, String actionName) {
        ((ControlPanel) controlPanel).setControlPanel(gameInfo, actionName);
    }

    public void setGamePanel(GameInfo gameInfo) {
        ((GamePanel) gamePanel).setGamePanel(gameInfo);
    }

    public void setInfoPannel(GameInfo gameInfo) {
        ((InfoPanel) infoPanel).setInfoPannel(gameInfo);
    }

    public void setMessage(GameInfo gameInfo, String message) {
        ((GamePanel) gamePanel).setMessage(gameInfo, message);
    }


    private void setupFrame() {
//        this.setContentPane(basePanel);
        setLocationPanel();
        setFrameDisplay();
    }
	
	public void setFrameDisplay() {
		setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setLocationPanel() {
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout());

		mainContainer.add(infoPanel, BorderLayout.NORTH);

        mainContainer.add(gamePanel, BorderLayout.CENTER);
		
//		cardPanel = new JPanel();
//        cardPanel.setLayout(new GridLayout(1, 5));
        
//        JLabel[] cardLabels = new JLabel[NO_OF_CARDS];

//        for (int i = 0; i < NO_OF_CARDS; i++) {
//            cardLabels[i] = new JLabel(new ImageIcon("/edu/sjsj/cs/cs151/images/card_frame.png"));
//            cardPanel.add(cardLabels[i]);
//        }
//        mainContainer.add(cardPanel, BorderLayout.CENTER);
        
        mainContainer.add(controlPanel, BorderLayout.SOUTH);

		mainContainer.add(playerPanel1, BorderLayout.WEST);
		
		mainContainer.add(playerPanel2, BorderLayout.EAST);
	}
}
