package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Message.ActionCheckMessage;
import edu.sjsu.cs.cs151.Message.Message;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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

    public void dispose() {

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

    public void setPlayerPannel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setPlayerPannel(gameInfo);
            }
        });
    }

    public void setControlPannel(GameInfo gameInfo, String actionName) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setControlPanel(gameInfo, actionName);
            }
        });
    }

    public void setMessage(GameInfo gameInfo, String messsage) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setMessage(gameInfo, messsage);
            }
        });
    }

    public void setGamePanel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setGamePanel(gameInfo);
            }
        });
    }

    public void setInfoPannel(GameInfo gameInfo) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((MainFrame)mainFrame).setInfoPannel(gameInfo);
            }
        });
    }



    public void addMessage() {

    }



}
