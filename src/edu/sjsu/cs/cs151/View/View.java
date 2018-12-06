package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.Message;
import javax.swing.*;
import java.util.concurrent.BlockingQueue;

/**
 * Main Frame of the Game
 */
public class View extends JFrame {

    private static JFrame mainFrame;
    private static BlockingQueue<Message> messageQueue;
    private static View view;

    public View() {
        mainFrame = MainFrame.init(messageQueue);
    }

    /**
     *  Setup Frame for the Game
     */
    public static View init(BlockingQueue<Message> queue) {
        if (view == null) {
            messageQueue = queue;
            view = new View();
        }
        return view;
    }

    /**
     * Set up the Player Panel on View
     * @param gameInfo
     */
    public void setPlayerPannel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setPlayerPannel(gameInfo);
            }
        });
    }

    /**
     * Set up the Control Panel on View
     * @param gameInfo
     * @param actionName
     */
    public void setControlPannel(GameInfo gameInfo, String actionName) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setControlPanel(gameInfo, actionName);
            }
        });
    }

    /**
     * Set up the message on View
     * @param gameInfo
     * @param messsage
     */
    public void setMessage(GameInfo gameInfo, String messsage) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setMessage(gameInfo, messsage);
            }
        });
    }

    /**
     * Set up the Game Panel on View
     * @param gameInfo
     */
    public void setGamePanel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setGamePanel(gameInfo);
            }
        });
    }

    /**
     * Set up the Information Panel on View
     * @param gameInfo
     */
    public void setInfoPannel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setInfoPannel(gameInfo);
            }
        });
    }
}
