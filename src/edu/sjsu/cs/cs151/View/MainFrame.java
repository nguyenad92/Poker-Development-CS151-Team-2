package edu.sjsu.cs.cs151.View;
import edu.sjsu.cs.cs151.Message.Message;

import java.util.concurrent.BlockingQueue;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private JPanel controlPanel, playerPanel1, playerPanel2, infoPanel, gamePanel;

    private static final int DEFAULT_HEIGHT = 900;
    private static final int DEFAULT_WIDTH = 600;

    public MainFrame() {
        infoPanel       = new InfoPanel(messageQueue);
        controlPanel    = new ControlPanel(messageQueue);
        gamePanel       = new GamePanel(messageQueue);
        playerPanel1    = new PlayerPanel(messageQueue);
        playerPanel2    = new PlayerPanel(messageQueue);
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
    	((PlayerPanel) playerPanel1).setPlayerPanel(gameInfo, 0);
    	((PlayerPanel) playerPanel2).setPlayerPanel(gameInfo, 1);
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
        setLocationPanel();
        setFrameDisplay();
    }
	
	private void setFrameDisplay() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setLocationPanel() {
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout());

		mainContainer.add(infoPanel, BorderLayout.NORTH);

        mainContainer.add(gamePanel, BorderLayout.CENTER);
		
        mainContainer.add(controlPanel, BorderLayout.SOUTH);

		mainContainer.add(playerPanel1, BorderLayout.WEST);
		
		mainContainer.add(playerPanel2, BorderLayout.EAST);
	}
}
