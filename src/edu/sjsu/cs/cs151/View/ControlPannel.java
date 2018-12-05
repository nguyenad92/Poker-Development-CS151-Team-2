package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;
import edu.sjsu.cs.cs151.Message.ActionCallMessage;
import edu.sjsu.cs.cs151.Message.ActionCheckMessage;
import edu.sjsu.cs.cs151.Message.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPannel extends JPanel {
    public ControlPannel(Controller baseControllera) {

        /**
         * Button Pannel
         */
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel);

        JButton checkbutton = new JButton("Check");
        buttonPanel.add(checkbutton);
        checkbutton.setBounds(0,500,200,80);

        JButton callbutton = new JButton("Call");
        buttonPanel.add(callbutton);
        callbutton.setBounds(100,500,200,80);

        JButton raisebutton = new JButton("Raise");
        buttonPanel.add(raisebutton);
        raisebutton.setBounds(200,500,200,80);

        JButton foldbutton = new JButton("Fold");
        buttonPanel.add(foldbutton);
        foldbutton.setBounds(300,500,200,80);

        JButton betbutton = new JButton("Bet");
        buttonPanel.add(betbutton);
        betbutton.setBounds(400,500,200,80);


        checkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new ActionCheckMessage();
                JOptionPane.showMessageDialog(null, m.getName() + ", " + m.getAmount());
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

    }

}
