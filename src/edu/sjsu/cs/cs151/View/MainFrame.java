package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.Message;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;

public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private JPanel basePanel = new TablePanel();
    private JPanel infoPanel, playerPanel, controlPanel, gamePanel;

    public MainFrame() {

//        PlayerPannel = new PlayerPanel(messageQueue, "Calvin", 10000);
        controlPanel = new ControlPanel(messageQueue);
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
        ((GamePannel) gamePanel).setGamePanel(gameInfo);
    }

    public void setInfoPannel(GameInfo gameInfo) {
        ((InfoPannel) infoPanel).setInfoPannel(gameInfo);
    }


    private void setupFrame() {
        this.setContentPane(basePanel);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(controlPanel);

//        this.add(playerPanel);


//        this.add(playerPanel);
//
//        JButton buttonNewGame = new JButton("NewGame");
//        buttonNewGame.addActionListener(new NewGameListener());
//        this.add(buttonNewGame);
//        return view;
    }

}
