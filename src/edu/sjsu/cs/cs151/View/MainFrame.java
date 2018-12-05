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
    private JPanel basePanel = new TablePanel();
    private JPanel topPanel, cardPanel, controlPanel,
            playerPanel1, playerPanel2, infoPanel;

    public MainFrame() {

//        PlayerPannel = new PlayerPanel(messageQueue, "Calvin", 10000);
        controlPanel = new ControlPanel(messageQueue);
        infoPanel = new InfoPanel(messageQueue);
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
        System.out.println("This is Dealer Position: " + gameInfo.getDealerPlayer().getName() + ": " + gameInfo.getDealerPlayer().getMoney());
        System.out.println("This is Dealer Position: " + gameInfo.getCurrentPlayer().getName() + ": " + gameInfo.getCurrentPlayer().getMoney());
    }

    public void setControlPanel(String actionName) {
        ((ControlPanel) controlPanel).setControlPanel(actionName);
    }

    public void setGamePanel(GameInfo gameInfo) {
        ((GamePanel) gamePanel).setGamePanel(gameInfo);
    }

    public void setInfoPannel(GameInfo gameInfo) {
        ((InfoPanel) infoPanel).setInfoPannel(gameInfo);
    }


    private void setupFrame() {
        this.setContentPane(basePanel);
        this.add(controlPanel);
        this.add(infoPanel);
        this.setSize(500, 500);
        this.setVisible(true);

//        this.add(playerPanel);


//        this.add(playerPanel);
//
//        JButton buttonNewGame = new JButton("NewGame");
//        buttonNewGame.addActionListener(new NewGameListener());
//        this.add(buttonNewGame);
//        return view;
    }

	private static BlockingQueue<Message> queue = null;
	
	private static GamePanel gamePanel = null;
	
	public static final int DEFAULT_HEIGHT = 900;
	
	public static final int DEFAULT_WIDTH = 600;
	
	public static final int NO_OF_CARDS = 5;

	private void GamePanel() {
		setFrameDisplay();
		setLocationPanel();
		setVisible(true);
	}

//    private GameFrame() {
//        //prywatne metody dla tego konstruktora
//        setFrameDisplay();
//        setLocationPanel();
//        setVisible(true);
//    }
	
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

		playerPanel1 = new PlayerPanel(messageQueue);
		mainContainer.add(playerPanel1, BorderLayout.WEST);
		
		playerPanel2 = new PlayerPanel(messageQueue);
		mainContainer.add(playerPanel2, BorderLayout.EAST);
	}
}
