package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame {

    private TablePanel basePanel;
    private BlockingQueue<Message> queue;

    public View() {

    }

    public View(Controller baseController) {
        basePanel = new TablePanel(baseController);
        setupFrame();
    }

    public View init(BlockingQueue<Message> queue) {
        this.queue = queue;
        return new View();
    }

    public void dispose() {

    }

    public void setupFrame() {
        this.setContentPane(basePanel);
        this.setSize(500, 500);
        this.setVisible(true);
        JPanel panel = new JPanel();
        this.add(panel);
        JButton checkbutton = new JButton("Check");
        panel.add(checkbutton);
        checkbutton.setBounds(0,260,100,40);
        JButton callbutton = new JButton("Call");
        panel.add(callbutton);
        callbutton.setBounds(100,260,100,40);
        JButton raisebutton = new JButton("Raise");
        panel.add(raisebutton);
        raisebutton.setBounds(200,260,100,40);
        JButton foldbutton = new JButton("Fold");
        panel.add(foldbutton);
        foldbutton.setBounds(300,260,100,40);
        JButton betbutton = new JButton("Bet");
        panel.add(betbutton);
        betbutton.setBounds(400,260,100,40);
        checkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        callbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        raisebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        foldbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        betbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        JLabel Pot = new JLabel("", (int) JLabel.LEFT_ALIGNMENT);
//        JLabel CurrentBet = new JLabel("", (int) JLabel.RIGHT_ALIGNMENT);
    }
}
