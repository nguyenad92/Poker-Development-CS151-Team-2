package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.Message;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;

public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private JPanel basePanel = new TablePanel();
    private JPanel infoPannel, PlayerPannel, controlPanel, GamePannel;

    public MainFrame() {
        setupFrame();
//        PlayerPannel = new PlayerPanel(messageQueue, "Calvin", 10000);
        controlPanel = new ControlPanel();
    }

    public static MainFrame init(BlockingQueue<Message> queue) {
        if (mainFrame == null) {
            messageQueue = queue;
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    public void setPlayerPannel(GameInfo gameInfo) {

    }

    public void setControlPanel(String actionName) {
        ((ControlPanel) controlPanel).setControlPanel(actionName);
    }


    private void setupFrame() {
        this.setContentPane(basePanel);
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

}
