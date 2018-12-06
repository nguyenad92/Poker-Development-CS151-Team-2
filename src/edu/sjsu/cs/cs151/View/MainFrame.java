package edu.sjsu.cs.cs151.View;
import edu.sjsu.cs.cs151.Message.Message;

import java.util.concurrent.BlockingQueue;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main frame that contains all panels and information
 */
public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private JPanel controlPanel, playerPanel1, playerPanel2, infoPanel, gamePanel;

    private static final int DEFAULT_HEIGHT = 500;
    private static final int DEFAULT_WIDTH = 800;

    public MainFrame() {
        infoPanel       = new InfoPanel(messageQueue);
        controlPanel    = new ControlPanel(messageQueue);
        gamePanel       = new GamePanel(messageQueue);
        playerPanel1    = new PlayerPanel();
        playerPanel2    = new PlayerPanel();
        setupFrame();
    }

    /**
     * Initiate the frame
     * @param queue
     * @return
     */
    public static MainFrame init(BlockingQueue<Message> queue) {
        if (mainFrame == null) {
            messageQueue = queue;
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    /**
     * Set up the players' panels that will be added into the frame
     * @param gameInfo
     */
    public void setPlayerPannel(GameInfo gameInfo) {
    	((PlayerPanel) playerPanel1).setPlayerPanel(gameInfo, 0);
    	((PlayerPanel) playerPanel2).setPlayerPanel(gameInfo, 1);
    }

    /**
     * Set up the Control Panel that will be added into the frame
     * @param gameInfo
     * @param actionName
     */
    public void setControlPanel(GameInfo gameInfo, String actionName) {
        ((ControlPanel) controlPanel).setControlPanel(gameInfo, actionName);
    }

    /**
     * Set up the Game Panel that will be added into the frame
     * @param gameInfo
     */
    public void setGamePanel(GameInfo gameInfo) {
        ((GamePanel) gamePanel).setGamePanel(gameInfo);
    }

    /**
     * Set up the Information Panel that will be added into the frame
     * @param gameInfo
     */
    public void setInfoPannel(GameInfo gameInfo) {
        ((InfoPanel) infoPanel).setInfoPannel(gameInfo);
    }

    /**
     * Set up the Message on the frame
     * @param gameInfo
     * @param message
     */
    public void setMessage(GameInfo gameInfo, String message) {
        ((GamePanel) gamePanel).setMessage(gameInfo, message);
    }

    /**
     * Set up the frame that will show all the panels
     */
    private void setupFrame() {
        setLocationPanel();
        setFrameDisplay();
    }

    /**
     * Set up the frame size and its properties
     */
	private void setFrameDisplay() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    /**
     * Adding all panel into container that will show on the frame
     */
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
