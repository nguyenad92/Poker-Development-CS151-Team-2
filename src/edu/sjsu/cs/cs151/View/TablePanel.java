package edu.sjsu.cs.cs151.View;

import edu.sjsu.cs.cs151.Controller.Controller;

import javax.swing.*;

public class TablePanel extends JPanel {

    private Controller baseController;

    public TablePanel(Controller baseController) {
        this.baseController = baseController;
    }
}
